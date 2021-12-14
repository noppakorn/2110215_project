package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.GameOver;

/**
 * The type Debug main.
 */
public class DebugMain extends Application {


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
        GameOver gameOver = new GameOver();
        Scene scene = new Scene(gameOver, 800, 600);
        stage.setScene(scene);
        stage.setTitle("fdausfislj");
        stage.show();
    }
}
