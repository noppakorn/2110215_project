package scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * The type Block.
 */
public class Block extends Pane {
    /**
     * Instantiates a new Block.
     *
     * @param blockType the block type
     */
    public Block(BlockType blockType) {
        this.setPrefHeight(50);
        this.setPrefWidth(50);
        this.setMinHeight(50);
        this.setMinWidth(50);
        String blockFileName = "grassBlock.png";
        switch (blockType) {
            case GRASS -> {
                blockFileName = "grassBlock.png";
            }
        }
        String imagePath = ClassLoader.getSystemResource(blockFileName).toString();
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        this.getChildren().add(imageView);
    }

    /**
     * Instantiates a new Block. Defaults to type BlockType.GRASS
     */
    public Block() {
        this(BlockType.GRASS);
    }

}
