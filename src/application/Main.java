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
import scene.GameOver;
import scene.LevelGenerator;
import scene.Menu;
import scene.Terrain;

import java.util.List;

/**
 * The type Main.
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
        if (!GameController.debugEnabled) {
            String bgMusicPath = ClassLoader.getSystemResource("mario.mp3").toString();
            Media media = new Media(bgMusicPath);
            MediaPlayer music = new MediaPlayer(media);
            music.play();
        }

        Menu menu = new Menu();
        Scene scene = new Scene(menu, 800, 600);
        GameController.initLevelGenerator(456478971L);
        stage.setScene(scene);
        stage.setTitle("Minerio");
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest((event) -> {
            GameController.setGameEnd();
            Platform.exit();
            System.exit(0);
        });
        new Thread(() -> {
            if (!GameController.debugEnabled) {
                while (!menu.gameStart) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Waiting for key press");
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
            while (!GameController.isGameEnd()) {
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
            if (!GameController.debugEnabled) {
                Platform.runLater(() -> {
                    GameOver gameOver = new GameOver();
                    scene.setRoot(gameOver);
                });
            }
        }).start();


    }
}