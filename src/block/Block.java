package application;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Block extends Pane {
    public Block() {
        this.setPrefHeight(50);
        this.setPrefWidth(50);
        this.setMinHeight(50);
        this.setMinWidth(50);
        String imagePath = ClassLoader.getSystemResource("block-test.png").toString();
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        this.getChildren().add(imageView);
    }

}
