package scene;


import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Terrain.
 * Represents the terrain of each scene in the level
 */
public class Terrain extends GridPane {
    private Random terrainRandom;
    private List<String> backgroundBlockType;

    /**
     * Instantiates a new Terrain.
     */
    public Terrain() {
        this(0);
    }


    /**
     * Instantiates a new Terrain.
     *
     * @param seed the seed
     */
    public Terrain(long seed) {
        super();
        terrainRandom = new Random(seed);
        backgroundBlockType = new ArrayList<>();
        backgroundBlockType.add("Stone");
        backgroundBlockType.add("Cobblestone");
        this.setMinHeight(600);
        this.setMaxHeight(600);
        this.setMinWidth(800);
        this.setMaxWidth(800);
        initializeTerrain("Empty");
    }


    /**
     * Initialize terrain background
     *
     * @param backgroundType the background type that can be "Stone" or "Empty"
     */
    public void initializeTerrain(String backgroundType) {
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (backgroundType.equals("Stone")) {
                    this.add(genStoneBackground(), j, i); // Stone Background
                } else {
                    this.add(new Block(), j, i); // Empty Background
                }
            }
        }
        for (int j = 0; j < 16; ++j) {
            Block grassBlock = new Block("Grass");
            this.add(grassBlock, j, 9);
        }
        for (int i = 10; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                Block block = new Block("Dirt");
                this.add(block, j, i);
            }
        }
        System.out.println(backgroundType + " Background Terrain initialized");
    }


    /**
     * Generate a stone background.
     *
     * @return the pane
     */
    private Pane genStoneBackground() {
        Block block = new Block(backgroundBlockType.get(terrainRandom.nextInt(2)));
        return block;
    }

}
