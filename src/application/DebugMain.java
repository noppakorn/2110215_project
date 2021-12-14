package application;

import exception.InvalidLevelException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import scene.ErrorMessageBox;
import scene.GameOver;

import java.util.Scanner;

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
        stage.setTitle("Debug Main");
        stage.show();
        new Thread( () -> {
            while (true) {
                Scanner kb = new Scanner(System.in);
                try {
                    int levelInput = kb.nextInt();
                    if (levelInput > 10) {
                        throw new InvalidLevelException(1, 10);
                    }
                    break;
                } catch (InvalidLevelException e) {
                    e.printStackTrace();
                    Platform.runLater(() -> {
                        ErrorMessageBox error = new ErrorMessageBox(e.getMessage());
                        error.showAndWait();
                    });
                }
            }
        }).start();
    }
}
