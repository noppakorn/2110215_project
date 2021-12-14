package controller;

import entity.base.Despawnable;
import entity.base.Entity;
import entity.derived.Box;
import entity.derived.Coin;
import entity.derived.CoinBox;
import entity.derived.Enemy;
import entity.derived.Player;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import scene.LevelGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that hold all the data related to the state of the game.
 */
public class GameController {
    /**
     * Set game to debug mode
     */
    public static final boolean debugEnabled = false;
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
    private static Text statusText = new Text();
    private static LevelGenerator levelGenerator;

    /**
     * Init level generator.
     *
     * @param seed the seed
     */
    public static void initLevelGenerator(long seed) {
        levelGenerator = new LevelGenerator(seed);
    }


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
    public static Text getStatusText() {
        return statusText;
    }

    /**
     * Start the game timer.
     */
    public static void startStatusText() {
        statusText.setX(200);
        statusText.setY(30);
        statusText.setTextAlignment(TextAlignment.CENTER);
        statusText.setFont(new Font("Arial", 30));
        Platform.runLater(() -> {
            statusText.setText(String.format("Time: %02d:%02d  Money: %d  Point: %d", minTimeElapsed, secTimeElapsed, money, point));
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
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    statusText.setText(String.format("Time: %02d:%02d  Money: %d  Point: %d", minTimeElapsed, secTimeElapsed, money, point));
                });
            }
        }).start();
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
                    if (player.getX() <= entity.getX() + entity.getFitWidth() && player.getX() + player.getFitWidth() >= entity.getX()) {
                        if (player.getY() <= entity.getY() + entity.getFitHeight() && player.getY() > entity.getY()) {
                            if (entity instanceof CoinBox && !((CoinBox) entity).getIsEmpty()) {
                                ((CoinBox) entity).setIsEmpty(true);
                                ((CoinBox) entity).coinPop();
                            }
                            if (player.isUpEnabled()) {
                                player.setVelocityY(-1 * player.getVelocityY());
                            }
                            player.setUpEnabled(false);
                            while (!player.isOnTheGround()) {
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            player.setVelocityY(0);
                            player.setUpEnabled(true);
                        } else if (player.getY() + player.getFitHeight() > entity.getY() && player.getY() + player.getFitHeight() < entity.getY() + entity.getFitHeight()) {
                            if (player.isDownEnabled()) {
                                player.setCorY(entity.getY() - player.getFitHeight());
                                player.setVelocityY(0);
                                player.setDownEnabled(false);
                                player.setJumping(false);
                            }
                            while (!(player.getX() + player.getFitWidth() < entity.getX() || player.getX() > entity.getX() + entity.getFitWidth() || player.isDownEnabled())) {
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            player.setDownEnabled(true);
                        }
                    }

                    if (player.getY() < entity.getY() + entity.getFitHeight() && player.getY() + player.getFitWidth() > entity.getY()) {
                        if (player.getX() + player.getFitWidth() >= entity.getX() && player.getX() + player.getFitWidth() < entity.getX() + entity.getFitWidth()) {
                            if (player.getRightEnabled()) {
                                player.setCorX(entity.getX() - player.getFitWidth());
                                player.setVelocityX(0);
                                player.setRightEnabled(false);
                            }
                        } else if (player.getX() <= entity.getX() + entity.getFitWidth() && player.getX() >= entity.getX()) {
                            if (player.getLeftEnabled()) {
                                player.setCorX(entity.getX() + entity.getFitWidth());
                                player.setVelocityX(0);
                                player.setLeftEnabled(false);
                            }
                        }
                    }
                } else if (entity instanceof Despawnable) {
                    if (player.getX() <= entity.getX() + entity.getFitWidth() && player.getX() + player.getFitWidth() >= entity.getX()
                            && player.getY() <= entity.getY() && player.getY() + player.getFitHeight() >= entity.getY() + entity.getFitHeight()) {
                        if (entity instanceof Coin) {
                            ((Coin) entity).collect();
                        } else if (entity instanceof Enemy) {
                            ((Enemy) entity).attack(player);
                        }
                    }
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
     * Gets texture loader instance.
     *
     * @return the texture loader
     */
    public static TextureLoader getTextureLoader() {
        return textureLoader;
    }

    /**
     * Increase money.
     *
     * @param dMoney the amount of money to add or remove
     */
    public static void increaseMoney(int dMoney) {
        money += dMoney;
    }

    /**
     * Gets level generator instance.
     *
     * @return the level generator
     */
    public static LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }

    /**
     * Gets time elapsed.
     *
     * @return the time elapsed
     */
    public static String getTimeElapsed() {
        return String.format("%02d:%02d", minTimeElapsed, secTimeElapsed);
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public static int getMoney() {
        return money;
    }
}