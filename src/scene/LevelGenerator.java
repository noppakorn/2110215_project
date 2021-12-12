package scene;

import entity.base.Entity;
import entity.derived.BoosterBlock;
import entity.derived.Coin;
import entity.derived.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Terrain generator.
 * Generate all the entities for the terrain
 */
public class LevelGenerator {
    private Random levelRandom;
    private List<Entity> entities;

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
        genTerrain();
    }

    /**
     * Generate the entity at random
     */
    public void genTerrain() {
        entities.clear();
        genCoins(randInt(1, 6));
        genEnemy(randInt(1, 6));
        genBoosterBlocks(randInt(1, 3));
    }

    /**
     * Generate a positive integer between [min, max) using terrainRandom
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
     * Generate coin entity.
     *
     * @param amount the amount to be generated
     */
    private void genCoins(int amount) {
        for (int i = 0; i < amount; ++i) {
            entities.add(new Coin(randInt(30, 600), randInt(200, 300)));
            System.out.println(entities.get(i));
        }
    }

    /**
     * Generate enemy entity.
     *
     * @param amount the amount to be generated
     */
    private void genEnemy(int amount) {
        for (int i = 0; i < amount; ++i) {
            entities.add(new Enemy("Enemy#" + amount));
        }
    }

    /**
     * Generate BoosterBlock entity.
     *
     * @param amount the amount to be generated
     */
    public void genBoosterBlocks(int amount) {
        for (int i = 0; i < amount; ++i) {
            entities.add(new BoosterBlock());
        }
    }

    /**
     * Get the entities that have been generated for this scene.
     *
     * @return the entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Remove the entity from the Terrain.
     *
     * @param e the entity to be removed
     * @return the boolean representing the status of the removal
     */
    public boolean removeEntities(Entity e) {
        return entities.remove(e);
    }
}