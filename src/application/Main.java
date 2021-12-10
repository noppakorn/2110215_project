package application;

import entity.derived.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import scene.Menu;
import scene.Terrain;

/**
 * The type Main.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*
        Start method for the program
         */
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
            Terrain terrain = new Terrain();
            Group mainScene = new Group();
            Player p = new Player();
            mainScene.getChildren().addAll(terrain, p);
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
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            mainScene.getChildren().remove(terrain);
                        }
                    }) ;
                    terrain = new Terrain();
                    terrain.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    p.returnToBegin();
                    p.setGoNextScene(false);
                    mainScene.getChildren().add(terrain);
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