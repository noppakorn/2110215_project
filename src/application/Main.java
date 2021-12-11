package application;

import entity.base.Entity;
import entity.derived.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import scene.Menu;
import scene.Terrain;
import scene.TerrainGenerator;

import java.util.List;

/**
 * The type Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        if (!GameController.debugEnabled) {
            String bgMusicPath = ClassLoader.getSystemResource("mario.mp3").toString();
            Media media = new Media(bgMusicPath);
            MediaPlayer player = new MediaPlayer(media);
            player.play();
        }

        Menu menu = new Menu();
        Scene scene = new Scene(menu, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Poprio");
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
            Group mainScene = new Group();
            Terrain terrain = new Terrain();
            Player player = new Player();
            TerrainGenerator terrainGenerator = new TerrainGenerator(7689746521534L);
            mainScene.getChildren().add(terrain);
            mainScene.getChildren().addAll(terrainGenerator.getEntities());
            mainScene.getChildren().add(player);
            Platform.runLater(() -> {
                scene.setRoot(mainScene);
            });
            while (!GameController.isGameEnd()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Entity> toBeRemoved = GameController.checkCollision(player, terrainGenerator);
                if (toBeRemoved.size() > 0) {
                    Platform.runLater(() -> {
                        mainScene.getChildren().removeAll(toBeRemoved);
                    });
                }
                if (player.isGoNextScene()) {
                    player.setGoNextScene(false);
                    Platform.runLater(() -> {
                        mainScene.getChildren().clear();

                        terrainGenerator.genTerrain();
                        player.returnToBegin();

                        mainScene.getChildren().add(terrain);
                        mainScene.getChildren().addAll(terrainGenerator.getEntities());
                        mainScene.getChildren().add(player);
                    });
                }
            }
        }).start();
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}