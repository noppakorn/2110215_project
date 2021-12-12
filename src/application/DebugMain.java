package application;

import initializer.TextureLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

/**
 * The type Debug main.
 */
public class DebugMain extends Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        TextureLoader t = new TextureLoader();
        ImageView imageView = new ImageView(t.getBlockImage("Dirt"));

        group.getChildren().add(imageView);
        Scene scene = new Scene(group, 2048, 2048);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Poprio");
        stage.setResizable(false);
    }
}
