package application;

import entity.base.Attackable;
import entity.base.Collectable;
import entity.base.Despawnable;
import entity.base.Entity;
import entity.derived.Player;
import initializer.TextureLoader;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import scene.TerrainGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that hold all the data related to the state of the game.
 */
public class GameController {
    /**
     * Set game to debug mode
     */
    public static final boolean debugEnabled = true;
    /**
     * The Terrain count for identifying the number of terrain generated.
     */
    protected static int terrainCount = 0;
    /**
     * Accumulated points for player
     */
    private static int point = 0;
    private static int money = 0;
    /**
     * Time Elapsed in seconds for the current level
     */
    private static int timeElapsed = 0;
    private static boolean isGameEnd = false;
    private static TextureLoader textureLoader = new TextureLoader();
    private static ArrayList<Text> statusText = new ArrayList<>();


    /**
     * Gets points.
     *
     * @return the points
     */
    public static int getPoint() {
        return point;
    }

    /**
     * Increase point.
     *
     * @param dPoint the d point
     */
    public static void increasePoint(int dPoint) {
        point += dPoint;
    }

    /**
     * Gets list of status text.
     *
     * @return the status texts
     */
    public static ArrayList<Text> getStatusText() {
        return statusText;
    }

    public static void startStatusText() {
        timeUpdater();
        pointUpdater();
        moneyUpdater();
    }

    /**
     * Start the game timer.
     */
    private static void timeUpdater() {
        Text timerText = new Text();
        timerText.setX(200);
        timerText.setY(30);
        timerText.setTextAlignment(TextAlignment.CENTER);
        timerText.setFont(new Font("Arial", 30));
        Platform.runLater(() -> {
            timerText.setText("Time: " + timeElapsed);
        });
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    timerText.setText("Time: " + ++timeElapsed);
                });
            }
        }).start();
        statusText.add(timerText);
    }

    /**
     * Point updater.
     */
    private static void pointUpdater() {
        Text pointText = new Text();
        pointText.setX(600);
        pointText.setY(30);
        pointText.setTextAlignment(TextAlignment.CENTER);
        pointText.setFont(new Font("Arial", 30));
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    pointText.setText("Points: " + point);
                });
            }
        }).start();
        statusText.add(pointText);
    }

    /**
     * Money updater.
     */
    private static void moneyUpdater() {
        Text moneyText = new Text();
        moneyText.setX(400);
        moneyText.setY(30);
        moneyText.setTextAlignment(TextAlignment.CENTER);
        moneyText.setFont(new Font("Arial", 30));
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    moneyText.setText("Money: " + point);
                });
            }
        }).start();
        statusText.add(moneyText);
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
     * Gets texture loader.
     *
     * @return the texture loader
     */
    public static TextureLoader getTextureLoader() {
        return textureLoader;
    }

    public static void increaseMoney(int dMoney) {
        money += dMoney;
    }
}