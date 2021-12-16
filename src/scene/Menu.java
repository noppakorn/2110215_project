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
 * Welcome screen for the game.
 */
public class Menu extends VBox {
    /**
     * Indicate the state of the game.
     */
    public boolean isGameStart;

    /**
     * Instantiates a new Menu.
     */
    public Menu() {
        super();
        isGameStart = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        initializeTitle();
        initializeAuthor();
        initializeInstruction();
        initializeLevelSetter();
    }

    /**
     * Initialize the title text
     */
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

    /**
     * Initialize the author text
     */
    private void initializeAuthor() {
        Text text = new Text("By Meen and Ta");
        text.setStyle("-fx-font-weight: bold");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(new Font("Arial", 24));
        this.getChildren().add(text);
    }

    /**
     * Initialize the instruction text
     */
    private void initializeInstruction() {
        Text instructionText = new Text("How many level do you want to play?");
        instructionText.setTextAlignment(TextAlignment.CENTER);
        instructionText.setFont(new Font("Arial", 24));
        this.getChildren().add(instructionText);
    }

    /**
     * Initialize the text field and button for the user to put how many level do they want to play.
     */
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

    /**
     * Handle when the user input the level.
     * @param levelField the text field to read level from
     */
    private void handleLevelInput(TextField levelField) {
        try {
            int levelInput = Integer.parseInt(levelField.getText());
            GameController.initLevelGenerator(198208432L, levelInput);
            isGameStart = true;
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
     * Check if the game is started
     *
     * @return the boolean indicating if the game is started
     */
    public boolean isGameStart() {
        return isGameStart;
    }
}
