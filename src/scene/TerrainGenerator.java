package scene;

import entity.derived.BoosterBlock;
import entity.derived.Coin;
import entity.derived.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Terrain generator.
 */
public class TerrainGenerator {
    private Random terrainRand;
    private List<Enemy> enemies;
    private List<Coin> coins;
    private List<BoosterBlock> boosterBlocks;

    /**
     * Instantiates a new Terrain generator.
     */
    public TerrainGenerator() {
        this(0);
    }

    /**
     * Instantiates a new Terrain generator.
     *
     * @param seed the seed
     */
    public TerrainGenerator(long seed) {
        terrainRand = new Random();
        terrainRand.setSeed(seed);
        enemies = new ArrayList<>();
        coins = new ArrayList<>();
        boosterBlocks = new ArrayList<>();
        genTerrain();
    }
    public void genTerrain() {
        coins.clear();
        enemies.clear();
        boosterBlocks.clear();
        genCoins(randInt(1,6));
        genEnemy(randInt(1,6));
        genBoosterBlocks(randInt(1, 3));
    }

    /**
     * Generate a positive interger between [min, max) using terrainRandom
     * @param min the minimum value to be generated
     * @param max the maximum value to be generated + 1
     * @return A random integer
     */
    private int randInt(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException("Max should be more that min");
        }
        return (Math.abs(terrainRand.nextInt()) % (max - min)) + min;
    }

    /**
     * Gen coins.
     *
     * @param amount the amount
     */
    public void genCoins(int amount) {
        for (int i = 0; i < amount; ++i) {
            coins.add(new Coin(randInt(30, 700), randInt(200, 500)));
            System.out.println(coins.get(i));
        }
    }

    /**
     * Gen enemy.
     *
     * @param amount the amount
     */
    public void genEnemy(int amount) {
        for (int i = 0; i < amount; ++i) {
            enemies.add(new Enemy("Enemy: " + amount));
        }
    }

    /**
     * Gen booster blocks.
     *
     * @param amount the amount
     */
    public void genBoosterBlocks(int amount) {
        for (int i = 0; i < amount; ++i) {
            boosterBlocks.add(new BoosterBlock());
        }
    }

    /**
     * Gets enemies.
     *
     * @return the enemies
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets coins.
     *
     * @return the coins
     */
    public List<Coin> getCoins() {
        return coins;
    }

    /**
     * Gets booster blocks.
     *
     * @return the booster blocks
     */
    public List<BoosterBlock> getBoosterBlocks() {
        return boosterBlocks;
    }
}
