package application;

import entity.derived.Player;
import javafx.scene.Group;


public class GameController {
    public Player player;

    public void createGame(Group gameDisplay) {
        player = new Player();
        gameDisplay.getChildren().addAll(player);
    }

}
