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
 * Base scene for game ending.
 */
public abstract class GameEnd extends VBox {
    protected Text title;
    protected int retryOrExit;


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

    public int getRetryOrExit() {
        return retryOrExit;
    }
}
