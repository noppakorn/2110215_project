package scene;

import controller.GameController;
import entity.base.Entity;
import entity.derived.Box;
import entity.derived.Pipe;
import entity.derived.Coin;
import entity.derived.CoinBox;
import entity.derived.Enemy;
import exception.InvalidLevelException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Terrain generator.
 * Generate all the entities for the terrain
 */
public class LevelGenerator {
    /**
     * The Entities.
     */
    private List<Entity> entities;
    private Random levelRandom;
    private boolean levelGeneratorBusy;
    private int currentLevel;


    /**
     * Instantiates a new Terrain generator.
     *
     * @param seed          the seed for the random number generator
     * @param maxLevelToGen the max level to gen
     * @throws InvalidLevelException the invalid level exception
     */
    public LevelGenerator(long seed, int maxLevelToGen) throws InvalidLevelException {
        levelRandom = new Random();
        levelRandom.setSeed(seed);
        entities = new ArrayList<>();
        if (maxLevelToGen > 10) {
            throw new InvalidLevelException(1, 10);
        } else {
            currentLevel = 10 - maxLevelToGen;
        }
        genNextLevel();
    }

    /**
     * Generate the entities for current level
     */
    public void genNextLevel() {
        levelGeneratorBusy = true;
        entities.clear();
        switch (currentLevel) {
            case 0 -> {
                for (int i = 0; i < 1; ++i) {
                    genEntity("Coin", randInt(100, 700), 250);
                }
                genEntity("CoinBox", 400, 250);
                genEntity("CoinBox", 200, 250);
                ++currentLevel;
            }
            case 1 -> {
                genEntity("Coin", 300, 200);
                genEntity("Pipe", 200, 310);
                genEntity("CoinBox", 400, 250);
                genEntity("CoinBox", 500, 250);
                ++currentLevel;
            }
            case 2 -> {
                genEntity("Enemy", randInt(300, 400), 450);
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("Pipe", 150, 310);
                genEntity("CoinBox", 420, 250);
                genEntity("CoinBox", 540, 250);
                ++currentLevel;
            }
            case 3 -> {
                for (int i = 0; i < 1; ++i) {
                    genEntity("Enemy", randInt(300, 400), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("Pipe", 300, 310);
                genEntity("Pipe", 670, 310);
                genEntity("CoinBox", 180, 250);
                genEntity("CoinBox", 500, 250);
                ++currentLevel;
            }
            case 4 -> {
                for (int i = 0; i < 3; ++i) {
                    genEntity("Enemy", randInt(300, 500), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("CoinBox", 300, 250);
                genEntity("CoinBox", 350, 250);
                ++currentLevel;
            }
            case 5 -> {
                for (int i = 0; i < 4; ++i) {
                    genEntity("Enemy", randInt(300, 500), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("Pipe", 300, 310);
                genEntity("Pipe", 640, 310);
                genEntity("CoinBox", 470, 250);
                ++currentLevel;
            }
            case 6 -> {
                for (int i = 0; i < 2; ++i) {
                    genEntity("Enemy", randInt(300, 500), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("Pipe", 200, 310);
                genEntity("Pipe", 540, 310);
                genEntity("Pipe", 680, 310);
                genEntity("CoinBox", 370, 250);
                ++currentLevel;
            }
            case 7 -> {
                for (int i = 0; i < 6; ++i) {
                    genEntity("Enemy", randInt(300, 500), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("Pipe", 150, 310);
                genEntity("Pipe", 400, 310);
                genEntity("Pipe", 650, 310);
                ++currentLevel;
            }
            case 8 -> {
                for (int i = 0; i < 8; ++i) {
                    genEntity("Enemy", randInt(300, 500), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("Pipe", 200, 310);
                genEntity("Pipe", 600, 310);
                genEntity("CoinBox", 400, 250);
                ++currentLevel;
            }
            case 9 -> {
                for (int i = 0; i < 9; ++i) {
                    genEntity("Enemy", randInt(300, 500), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("CoinBox", 100, 250);
                genEntity("CoinBox", 300, 250);
                genEntity("CoinBox", 500, 250);
                genEntity("CoinBox", 700, 250);
                ++currentLevel;
            }
        }
        levelGeneratorBusy = false;
    }


    /**
     * Generate a positive integer between [min, max).
     *
     * @param min the minimum value to be generated
     * @param max the maximum value to be generated + 1
     * @return A random integer
     */
    private int randInt(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException("Max should be more that min");
        }
        return (Math.abs(levelRandom.nextInt()) % (max - min)) + min;
    }

    /**
     * Generate entity for the level.
     *
     * @param entityType The type of Entity to be generated
     */
    private void genEntity(String entityType, double x, double y) {
        Entity entity;
        switch (entityType) {
            case "Coin" -> entity = new Coin(x, y);
            case "Box" -> entity = new Box("Box", x, y);
            case "Enemy" -> entity = new Enemy("Enemy", x, y, randInt(-1, 1));
            case "Pipe" -> entity = new Pipe("Pipe", x, y);
            case "CoinBox" -> {
                CoinBox coinBox = new CoinBox("CoinBox", x, y);
                entities.add(coinBox.getCoinInCoinBox());
                entities.add(coinBox);
                return;
            }
            default -> throw new IllegalArgumentException("Invalid entity type name");
        }
        System.out.println(entity);
        entities.add(entity);
    }


    /**
     * Get the entities that have been generated for this level.
     *
     * @return the list of all entities currently in the level
     */
    public List<Entity> getEntities() {
        return entities;
    }


    /**
     * Is level generator busy boolean.
     *
     * @return the boolean
     */
    public boolean isLevelGeneratorBusy() {
        return levelGeneratorBusy;
    }

}
