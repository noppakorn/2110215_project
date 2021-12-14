package application;

import exception.InvalidLevelException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
        VBox vBox = new VBox();
        TextField levelField = new TextField();
        levelField.setPrefColumnCount(2);
        Button submitButton = new Button("Submit");
        submitButton.setOnAction((event) -> {
            try {
                long levelInput = Long.parseLong(levelField.getText());
                if (levelInput > 10) {
                    throw new InvalidLevelException(1, 10);
                }
            } catch (NumberFormatException e) {
                Platform.runLater(() -> {
                    ErrorMessageBox error = new ErrorMessageBox("Level should be an integer");
                    error.showAndWait();
                });
            } catch (InvalidLevelException e) {
                Platform.runLater(() -> {
                    ErrorMessageBox error = new ErrorMessageBox(e.getMessage());
                    error.showAndWait();
                });
            }
            levelField.clear();
        });
        vBox.getChildren().addAll(levelField, submitButton);
        Scene scene = new Scene(vBox, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Debug Main");
        stage.show();
    }
}
