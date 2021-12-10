package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import scene.Menu;
import scene.Terrain;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        String bgMusicPath = ClassLoader.getSystemResource("mario.mp3").toString();
//        Media media = new Media(bgMusicPath);
//        MediaPlayer player = new MediaPlayer(media);
//        player.play();

        Menu menu = new Menu();
        Scene scene = new Scene(menu, 800, 600, Color.LIGHTSKYBLUE);
        stage.setScene(scene);
        stage.setTitle("Poprio");
        stage.setResizable(false);
        stage.show();
        new Thread(() -> {
            while (!menu.gameStart) {
                System.out.println("Waiting for key press.");
            }
            Terrain terrain = new Terrain();
            Platform.runLater(() -> {
                scene.setRoot(terrain);
            });
        }).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}