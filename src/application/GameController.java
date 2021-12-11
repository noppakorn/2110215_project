package application;

import entity.base.Attackable;
import entity.base.Collectable;
import entity.base.Despawnable;
import entity.base.Entity;
import entity.derived.BoosterBlock;
import entity.derived.Coin;
import entity.derived.Enemy;
import entity.derived.Player;
import javafx.scene.Group;
import scene.Terrain;
import scene.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Check for collision between player and objects and do action
     *
     * @param player           the player
     * @param terrainGenerator the terrain generator
     * @return the List of Entities to be removed from the scene
     */
    public static List<Entity> checkCollision(Player player, TerrainGenerator terrainGenerator) {
        List<Entity> toBeRemoved = new ArrayList<>();

        for (Entity entity : terrainGenerator.getEntities()) {
            if (player.getX() <= entity.getX() && player.getX() + player.getFitWidth() >= entity.getX() + entity.getFitWidth()
                    && player.getY() <= entity.getY() && player.getY() + player.getFitHeight() <= entity.getY() + entity.getFitHeight()) {
                System.out.println(player + " collision occurred with " + entity);
                if (entity instanceof Collectable) {
                    System.out.println(entity + " Collected");
                    ((Collectable) entity).collect();
                } else if (entity instanceof Attackable) {
                    System.out.println(entity + " Attacked");
                }
            }
        }

        terrainGenerator.getEntities().removeIf(entity -> {
            if (entity instanceof Despawnable) {
                if (((Despawnable) entity).isDespawn()) {
                    toBeRemoved.add(entity);
                    return true;
                }
            }
            return false;
        });
        return toBeRemoved;
    }

}