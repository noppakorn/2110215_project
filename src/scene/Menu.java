package scene;

import controller.GameController;
import exception.InvalidLevelException;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Welcome screen for the game
 */
public class Menu extends VBox {
    /**
     * Indicate the state of the game
     */
    public boolean gameStart;
    /**
     * The Menu title.
     */
    private Text title;

    /**
     * Instantiates a new Menu.
     */
    public Menu() {
        super();
        gameStart = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        initializeTitle();
        initializeInstruction();
        initializeLevelSetter();
    }

    private void initializeTitle() {
        Text title = new Text("Minerio");
        title.setStyle("-fx-font-weight: bold");
        title.setFont(new Font("Arial", 72));
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), title);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
        this.getChildren().add(title);
    }

    private void initializeInstruction() {
        Text instructionText = new Text("How many level do you want to play?");
        instructionText.setTextAlignment(TextAlignment.CENTER);
        instructionText.setFont(new Font("Arial", 24));
        this.getChildren().add(instructionText);
    }

    private void initializeLevelSetter() {
        TextField levelField = new TextField();
        levelField.setMaxWidth(200);

        levelField.setOnKeyPressed((keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                handleLevelInput(levelField);
            }
        }));
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> handleLevelInput(levelField));

        this.getChildren().addAll(levelField, submitButton);
    }

    private void handleLevelInput(TextField levelField) {
        try {
            int levelInput = Integer.parseInt(levelField.getText());
            GameController.initLevelGenerator(198208432L, levelInput);
            gameStart = true;
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
    }

    /**
     * Is game start boolean.
     *
     * @return the boolean
     */
    public boolean isGameStart() {
        return gameStart;
    }
}
