package scene;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The type Game over.
 */
public class GameOver extends VBox {
    private Text title;
    private Text timeTook;
    private Text point;
    private Text money;

    /**
     * Instantiates a new GameOver screen with the Player stats
     */
    public GameOver() {
        super();
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));

        title = genAndAddText("You Died!");
        title.setFont(new Font("Arial", 72));
        title.setStyle("-fx-font-weight: bold");
        // TODO: show Player stats when Game Over
        timeTook = genAndAddText("Time: " + GameController.getTimeElapsed());
        money = genAndAddText("Money: " + GameController.getMoney());
        point = genAndAddText("Point: " + GameController.getPoint());
    }

    /**
     * Gen and add text text.
     *
     * @param textToGen the text to gen
     * @return the text
     */
    public Text genAndAddText(String textToGen) {
        Text text = new Text(textToGen);
        text.setFont(new Font("Arial", 32));
        text.setTextAlignment(TextAlignment.CENTER);
        this.getChildren().add(text);
        return text;
    }
}
