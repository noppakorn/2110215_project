package scene;


import block.Block;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Terrain.
 * Represents the terrain of each scene in the level
 */
public class Terrain extends GridPane {
    /**
     * Instantiates a new Terrain.
     */
    private int terrainID;
    /**
     * ID for identifying terrain set by GameController
     */

    /**
     * Instantiates a new Terrain.
     *
     * @param terrainID the terrain id
     */
    public Terrain(int terrainID) {
        super();
        this.setMinHeight(600);
        this.setMaxHeight(600);
        this.setMinWidth(800);
        this.setMaxWidth(800);
        initializeTerrain();
        this.terrainID = terrainID;
        readTerrainFile("terrain.txt");
    }

    /**
     * Gets terrain id.
     *
     * @return the terrain id
     */
    public int getTerrainID() {
        return terrainID;
    }


    /**
     * Initialize terrain.
     */
    public void initializeTerrain() {
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
        // Add empty filler
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                this.add(genEmptyPane(), i, j);
            }
        }
        // Add block to game
        for (int i = 9; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                Block block = new Block();
                this.add(block, j, i);
            }
        }
        System.out.println("Terrain Initialized");
    }

    public void readTerrainFile(String resourcePath) {
        List<String> levels = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(ClassLoader.getSystemResource(resourcePath).toString()));
            while (scanner.hasNextLine()) {
                levels.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(levels);
    }

    public Pane genEmptyPane() {
        Pane tmpPane = new Pane();
        tmpPane.setMaxHeight(50);
        tmpPane.setMaxWidth(50);
        tmpPane.setMinHeight(50);
        tmpPane.setMinWidth(50);
        return tmpPane;
    }

}
