package application;

import entity.derived.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.Menu;
import scene.Terrain;

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
        new Thread(() -> {
            Terrain terrain = new Terrain();
            Group mainScene = new Group();
            Player p = new Player();
            mainScene.getChildren().addAll(terrain, p);
            while (!menu.gameStart) {
                System.out.println("Waiting for key press.");
            }
            Platform.runLater(() -> {
                scene.setRoot(mainScene);
            });
        }).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}