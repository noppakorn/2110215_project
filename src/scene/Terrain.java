package scene;


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
    public Terrain() {
        super();
        this.setMinHeight(600);
        this.setMaxHeight(600);
        this.setMinWidth(800);
        this.setMaxWidth(800);
        initializeTerrain();
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


    /**
     * Gen empty pane pane.
     *
     * @return the pane
     */
    public Pane genEmptyPane() {
        Pane tmpPane = new Pane();
        tmpPane.setMaxHeight(50);
        tmpPane.setMaxWidth(50);
        tmpPane.setMinHeight(50);
        tmpPane.setMinWidth(50);
        return tmpPane;
    }

}
