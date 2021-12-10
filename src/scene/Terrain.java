package scene;


import block.Block;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Terrain extends GridPane {
    public Terrain() {
        super();
        this.setMinHeight(600);
        this.setMaxHeight(600);
        this.setMinWidth(800);
        this.setMaxWidth(800);
        initializeTerrain();
    }
    public void readTerrain() {
    }
    public void initializeTerrain() {
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
