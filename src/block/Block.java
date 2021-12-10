package block;

import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Block extends Pane {
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

    public Block() {
        this(BlockType.GRASS);
    }

}
