package application;

import entity.derived.Player;
import javafx.scene.Group;

import java.util.Timer;
import java.util.TimerTask;


/**
 * The type Game controller.
 */
public class GameController {
    /**
     * The Player.
     */
    private Player player;
    /**
     * Accumulated points for player
     */
    private int points;
    /**
     * Time Elapsed in seconds for the current level
     */
    private Timer timeElapsed;
    private boolean isGameEnd;

    public GameController() {
        player = new Player();
        this.points = 0;
        this.isGameEnd = false;
        startTimer();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void startTimer() {
        timeElapsed = new Timer();
        new Thread(() -> {
            timeElapsed.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("hello");
                    System.out.println(timeElapsed);
                }
            }, 1000);
        });
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        isGameEnd = gameEnd;
    }

    /**
     * Create game.
     *
     * @param gameDisplay the game display
     */
    public void genLevel(Group gameDisplay) {
        gameDisplay.getChildren().addAll(player);
    }
}