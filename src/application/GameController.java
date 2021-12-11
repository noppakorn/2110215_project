package application;

import entity.derived.Player;
import javafx.scene.Group;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Class that hold all the data related to the state of the game.
 */
public class GameController {
    /**
     * Set game to debug mode
     */
    public static final boolean debugEnabled = true;
    /**
     * Accumulated points for player
     */
    private static int points = 0;
    /**
     * The Terrain count for identifying the number of terrain generated.
     */
    protected static int terrainCount = 0;

    /**
     * Time Elapsed in seconds for the current level
     */
    private static Timer timeElapsed;
    private static boolean isGameEnd = false;


    /**
     * Gets points.
     *
     * @return the points
     */
    public static int getPoints() {
        return points;
    }

    /**
     * Increase point.
     *
     * @param dPoint the d point
     */
    public static void increasePoint(int dPoint) {
        points += dPoint;
    }

    /**
     * Start the game timer.
     */
    public static void startTimer() {
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
     * The state of the game.
     *
     * @return the boolean that represents the state of the game.
     */
    public static boolean isGameEnd() {
        return isGameEnd;
    }


    /**
     * Set game state to end.
     */
    public static void setGameEnd() {
        isGameEnd = true;
    }

}