package scene;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Scene for game ending.
 */
public class GameEnd extends VBox {
    /**
     * The Title.
     */
    protected Text title;
    /**
     * Indicating if the player want to retry or exit the game
     */
    protected int retryOrExit;


    /**
     * Instantiates a new Game end.
     *
     * @param isWin the is win
     */
    public GameEnd(boolean isWin) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        if (isWin) {
            this.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
            title = genAndAddText("You Win!");
        } else {
            this.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
            title = genAndAddText("You Died!");
        }

        title.setFont(new Font("Arial", 72));
        title.setStyle("-fx-font-weight: bold");
        genAndAddText("Time: " + GameController.getTimeElapsed());
        genAndAddText("Money: " + GameController.getMoney());
        genAndAddText("Point: " + GameController.getPoint());

        retryOrExit = 0;
        addButton();
    }


    /**
     * Create and add text with proper format to the scene.
     *
     * @param textToGen the text to gets generated
     * @return the text generated
     */
    protected Text genAndAddText(String textToGen) {
        Text text = new Text(textToGen);
        text.setFont(new Font("Arial", 32));
        text.setTextAlignment(TextAlignment.CENTER);
        this.getChildren().add(text);
        return text;
    }

    /**
     * Add button.
     */
    protected void addButton() {
        Button retryButton = new Button("Play Again");
        retryButton.setFont(new Font("Arial", 18));
        retryButton.setMinWidth(120);
        retryButton.setMinHeight(50);
        retryButton.setOnAction((event) -> retryOrExit = 1);
        Button exitButton = new Button("Exit");
        exitButton.setFont(new Font("Arial", 18));
        exitButton.setMinWidth(120);
        exitButton.setMinHeight(50);
        exitButton.setOnAction((event) -> retryOrExit = -1);
        this.getChildren().addAll(retryButton, exitButton);
    }

    /**
     * Gets retry or exit.
     *
     * @return the retry or exit
     */
    public int getRetryOrExit() {
        return retryOrExit;
    }
}
