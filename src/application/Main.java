package application;

import controller.GameController;
import entity.base.Entity;
import entity.derived.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import scene.GameEnd;
import scene.LevelGenerator;
import scene.Menu;
import scene.Terrain;

import java.util.List;

/**
 * The Main entrypoint of the application
 *
 * @author Noppakorn Jiravaranun
 * @author Nopparuj Poonsubanan
 */
public class Main extends Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MediaPlayer music = new MediaPlayer(new Media(ClassLoader.getSystemResource("mario.mp3").toString()));

        stage.setTitle("Minerio");
        stage.setResizable(false);
        stage.setOnCloseRequest((event) -> {
            GameController.setGameEnd();
            Platform.exit();
            System.exit(0);
        });
        new Thread(() -> {
            while (true) {
                GameController.resetGameController();
                Menu menu = new Menu();
                Scene scene = new Scene(menu, 800, 600);
                Platform.runLater(() -> {
                    stage.setScene(scene);
                    stage.show();
                    music.play();
                });
                if (!GameController.DEBUG_ENABLED) {
                    while (!menu.isGameStart()) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                GameController.startStatusText();
                Group group = new Group();
                Terrain terrain = new Terrain();
                Player player = new Player();
                LevelGenerator levelGenerator = GameController.getLevelGenerator();
                group.getChildren().add(terrain);
                group.getChildren().addAll(levelGenerator.getEntities());
                group.getChildren().addAll(GameController.getStatusText());
                group.getChildren().add(player);
                Platform.runLater(() -> {
                    scene.setRoot(group);
                });
                while (!GameController.isGameEnd() && !GameController.getLevelGenerator().isGameWin()) {
                    List<Entity> toBeRemoved = GameController.checkCollision(player, levelGenerator);
                    if (toBeRemoved.size() > 0) {
                        Platform.runLater(() -> {
                            group.getChildren().removeAll(toBeRemoved);
                        });
                    }
                    if (player.isGoNextScene()) {
                        player.setGoNextScene(false);
                        Platform.runLater(() -> {
                            group.getChildren().clear();

                            levelGenerator.genNextLevel();
                            group.getChildren().add(terrain);
                            group.getChildren().addAll(levelGenerator.getEntities());
                            group.getChildren().addAll(GameController.getStatusText());
                            group.getChildren().add(player);
                        });
                    }
                }
                // Show game over screen
                if (!GameController.DEBUG_ENABLED) {
                    MediaPlayer deadSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("GameOver.mp3").toString()));
                    GameEnd gameEnd = new GameEnd(GameController.getLevelGenerator().isGameWin());
                    Platform.runLater(() -> {
                        scene.setRoot(gameEnd);
                        if (!GameController.getLevelGenerator().isGameWin()) {
                            deadSound.play();
                            music.stop();
                        }
                    });
                    while (gameEnd.getRetryOrExit() == 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (gameEnd.getRetryOrExit() == -1) {
                        Platform.exit();
                        System.exit(0);
                    }
                    deadSound.stop();
                }
            }
        }).start();

    }
}