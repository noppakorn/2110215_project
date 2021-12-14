package scene;

import entity.base.Entity;
import entity.derived.Box;
import entity.derived.Cactus;
import entity.derived.Coin;
import entity.derived.CoinBox;
import entity.derived.Enemy;

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
     * Instantiates a new Terrain generator with default random seed
     */
    public LevelGenerator() {
        this(0);
    }

    /**
     * Instantiates a new Terrain generator.
     *
     * @param seed the seed for the random number generator
     */
    public LevelGenerator(long seed) {
        levelRandom = new Random();
        levelRandom.setSeed(seed);
        entities = new ArrayList<>();
        currentLevel = 0;
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
                genEntity("Enemy", randInt(300, 700), 450);
                for (int i = 0; i < 5; ++i) {
                    genEntity("Coin", randInt(100, 700), 250);
                }
                genEntity("Cactus", randInt(100, 700), 450);
                genEntity("CoinBox", randInt(100, 700), 250);
                ++currentLevel;
            }
            case 1 -> {
                for (int i = 0; i < 3; ++i) {
                    genEntity("Enemy", randInt(300, 700), 450);
                }
                genEntity("Coin", randInt(100, 700), 200);
                genEntity("Cactus", randInt(100, 700), 450);
                genEntity("CoinBox", randInt(100, 700), 250);
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
     * @param amount     The amount of Entity to be generated
     */
    private void genEntity(String entityType, double x, double y) {
        Entity entity;
        switch (entityType) {
            case "Coin" -> entity = new Coin(x, y);
            case "Box" -> entity = new Box("Box", x, y);
            case "Enemy" -> entity = new Enemy("Enemy", x, y);
            case "CoinBox" -> {
                CoinBox coinBox = new CoinBox("CoinBox", x, y);
                entities.add(coinBox.getCoinInCoinBox());
                entities.add(coinBox);
                return;
            }
            case "Cactus" -> {
                for (int j = 0; j < 3; ++j) {
                    entity = new Cactus("Cactus", x, 400 - 50 * j);
                    entities.add(entity);
                }
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
