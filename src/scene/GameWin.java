package scene;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The type Game win.
 */
public class GameWin extends GameEnd {
    /**
     * Instantiates a new Game win.
     */
    public GameWin() {
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
        this.setSpacing(20);

        title = genAndAddText("You Win!");
        title.setFont(new Font("Arial", 72));
        title.setStyle("-fx-font-weight: bold");
        genAndAddText("Time: " + GameController.getTimeElapsed());
        genAndAddText("Money: " + GameController.getMoney());
        genAndAddText("Point: " + GameController.getPoint());

        retryOrExit = 0;
        addButton();
    }
}
