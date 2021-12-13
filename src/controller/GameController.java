package controller;

import entity.base.*;
import entity.derived.Box;
import entity.derived.Cactus;
import entity.derived.Player;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import scene.LevelGenerator;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
     * Accumulated points for player
     */
    private static int point = 0;
    private static int money = 0;
    /**
     * Time Elapsed in seconds for the current level
     */
    private static int secTimeElapsed = 0;
    private static int minTimeElapsed = 0;
    private static boolean isGameEnd = false;
    private static TextureLoader textureLoader = new TextureLoader();
    private static ArrayList<Text> statusText = new ArrayList<>();
    private static LevelGenerator levelGenerator = new LevelGenerator(5646468575612L);


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

    /**
     * Start status text.
     */
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
            timerText.setText(String.format("Time: %02d:%02d", minTimeElapsed, secTimeElapsed));
        });
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (++secTimeElapsed == 60) {
                    secTimeElapsed = 0;
                    ++minTimeElapsed;
                }
                Platform.runLater(() -> {
                    timerText.setText(String.format("Time: %02d:%02d", minTimeElapsed, secTimeElapsed));
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
                    moneyText.setText("Money: " + money);
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
    public static List<Entity> checkCollision(Player player, LevelGenerator terrainGenerator) {
        List<Entity> toBeRemoved = new ArrayList<>();

        if (!GameController.getLevelGenerator().isLevelGeneratorBusy()) {
            for (Entity entity : terrainGenerator.getEntities()) {
                if (entity instanceof Box) {
                    if (player.getX() + player.getWidth() > entity.getX()
                            && player.getX() + player.getWidth() < entity.getX() + entity.getWidth()
                            && player.getY() - player.getHeight() <= entity.getY()) {
                        player.setCorY(entity.getY() + 40);
                        player.setVelocityY(-1 * player.getVelocityY());

                    } else if (player.getX() > entity.getX()
                            && player.getX() < entity.getX() + entity.getWidth()
                            && player.getY() - player.getHeight() <= entity.getY()) {
                        player.setCorY(entity.getY() + 40);
                        player.setVelocityY(-1 * player.getVelocityY());

                    }

                    // Temp Side Collision
//                    if (player.getY() + player.getFitWidth() >= entity.getY()) {
//                        if (player.getX() + player.getWidth() >= entity.getX() && player.getX() <= entity.getX() + entity.getFitWidth() / 2) {
//                            if (player.getRightEnabled()) {
//                                player.setX(player.getX());
//                                player.setVelocityX(0);
//                            }
//                            player.setRightEnabled(false);
//                    } else if (player.getX() >= entity.getX() + entity.getFitWidth()) {
//                        if (player.getLeftEnabled()) {
//                            player.setX(player.getX());
//                            player.setVelocityX(0);
//                        }
//                        player.setLeftEnabled(false);
//                        }
//                    } else {
//                        player.setLeftEnabled(true);
//                        player.setRightEnabled(true);
//                    }
                } else if (entity instanceof Despawnable) {
                    if (player.getX() <= entity.getX() && player.getX() + player.getFitWidth() >= entity.getX() + entity.getFitWidth()
                            && player.getY() <= entity.getY() && player.getY() + player.getFitHeight() >= entity.getY() + entity.getFitHeight()) {
                        System.out.println(player + " collision occurred with " + entity);
                        if (entity instanceof Collectable) {
                            System.out.println(entity + " Collected");
                            ((Collectable) entity).collect();
                        } else if (entity instanceof Attackable) {
                            System.out.println(player + " attacked by " + entity);
                            ((Attackable) entity).attack(player);
                        }
                    }
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
        if (player.isDespawn()) {
            toBeRemoved.add(player);
            setGameEnd();
        }
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

    /**
     * Increase money.
     *
     * @param dMoney the d money
     */
    public static void increaseMoney(int dMoney) {
        money += dMoney;
    }

    /**
     * Gets level generator.
     *
     * @return the level generator
     */
    public static LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }
}