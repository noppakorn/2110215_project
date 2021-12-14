package scene;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The type Game over.
 */
public class GameOver extends GameEnd {
    /**
     * Instantiates a new GameOver screen with the Player stats
     */
    public GameOver() {
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        this.setSpacing(20);

        title = genAndAddText("You Died!");
        title.setFont(new Font("Arial", 72));
        title.setStyle("-fx-font-weight: bold");
        genAndAddText("Time: " + GameController.getTimeElapsed());
        genAndAddText("Money: " + GameController.getMoney());
        genAndAddText("Point: " + GameController.getPoint());

        retryOrExit = 0;
        addButton();
    }
}
