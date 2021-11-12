package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Main extends Application {
    private AnimationTimer animationTimer;
    private long timeTriggered;
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800,600, Color.LIGHTSKYBLUE);
        stage.setScene(scene);
        stage.setTitle("Poprio");
        stage.setResizable(false);
        stage.show();
        gametest m = new gametest();
        m.createGame(root);
    }


    public static void main(String[] args) {
        launch(args);
    }
}