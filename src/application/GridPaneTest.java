package application;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TilePaneTest extends Application {
    public void start(Stage stage) {
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 800, 600, Color.LIGHTSKYBLUE);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
