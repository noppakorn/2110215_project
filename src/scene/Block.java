package scene;

import controller.GameController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * The type Block.
 */
public class Block extends Pane {
    /**
     * Instantiates a new Block.
     *
     * @param blockName the resource name
     */
    public Block(String blockName) {
        this.setPrefHeight(50);
        this.setPrefWidth(50);
        this.setMinHeight(50);
        this.setMinWidth(50);
        if (!blockName.equals("Air")) {
            ImageView imageView = new ImageView(GameController.getTextureLoader().getBlockImage(blockName));
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            this.getChildren().add(imageView);
        }
    }

    /**
     * Instantiates a new Block. Defaults to Dirt Block
     */
    public Block() {
        this("Air");
    }
}

