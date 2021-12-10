package application;

import entity.derived.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.Menu;
import scene.Terrain;

/**
 * The type Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        String bgMusicPath = ClassLoader.getSystemResource("mario.mp3").toString();
//        Media media = new Media(bgMusicPath);
//        MediaPlayer player = new MediaPlayer(media);
//        player.play();

        Menu menu = new Menu();
        Scene scene = new Scene(menu, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Poprio");
        stage.setResizable(false);
        stage.show();
        GameController controller = new GameController();
        stage.setOnCloseRequest((event) -> controller.setGameEnd(true));
        new Thread(() -> {
            while (!menu.gameStart) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting for key press");
            }
            Group mainScene = new Group();
            Player p = new Player();
            p.setGoNextScene(true);
            Platform.runLater(() -> {
                scene.setRoot(mainScene);
            });
            while (!controller.isGameEnd()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (p.isGoNextScene()) {
                    p.setGoNextScene(false);
                    Terrain tmpTerrain = new Terrain(controller.terrainCount++);
                    Platform.runLater(() -> {
                        mainScene.getChildren().clear();
                        p.returnToBegin();
                        mainScene.getChildren().addAll(tmpTerrain, p);
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