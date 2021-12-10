package scene;


import block.Block;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
    }

    /**
     * Read terrain.
     */
    public void readTerrain() {
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
        for (int i = 0; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                Pane tmpPane = new Pane();
                tmpPane.setMaxHeight(50);
                tmpPane.setMaxWidth(50);
                tmpPane.setMinHeight(50);
                tmpPane.setMinWidth(50);
                this.add(tmpPane, i, j);
            }
        }
        for (int i = 9; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                Block block = new Block();
                this.add(block, j, i);
            }
        }
        System.out.println("Terrain Initialized");
    }
}
