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
    /**
     * The Terrain count.
     */
    protected int terrainCount;

    /**
     * Instantiates a new Game controller.
     */
    public GameController() {
        player = new Player();
        this.points = 0;
        this.isGameEnd = false;
        this.terrainCount = 0;
        startTimer();
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets points.
     *
     * @param points the points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Start timer.
     */
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

    /**
     * Is game end boolean.
     *
     * @return the boolean
     */
    public boolean isGameEnd() {
        return isGameEnd;
    }


    /**
     * Sets game end.
     *
     * @param gameEnd the game end
     */
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