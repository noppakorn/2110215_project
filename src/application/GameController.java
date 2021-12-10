package application;

import entity.derived.Player;
import javafx.scene.Group;


/**
 * The type Game controller.
 */
public class GameController {
    /**
     * The Player.
     */
    public Player player;

    /**
     * Create game.
     *
     * @param gameDisplay the game display
     */
    public void createGame(Group gameDisplay) {
        player = new Player();
        gameDisplay.getChildren().addAll(player);
    }

}
