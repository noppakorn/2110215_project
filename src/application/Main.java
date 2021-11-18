package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.media.Media;

public class Main extends Application {
    private AnimationTimer animationTimer;
    private long timeTriggered;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.LIGHTSKYBLUE);
        String bgMusicPath = ClassLoader.getSystemResource("mario.mp3").toString();
        Media media = new Media(bgMusicPath);
        MediaPlayer player = new MediaPlayer(media);

        stage.setScene(scene);
        stage.setTitle("Poprio");
        stage.setResizable(false);
        stage.show();
        player.play();
        GameTest m = new GameTest();
        m.createGame(root);
    }


    public static void main(String[] args) {
        launch(args);
    }
}