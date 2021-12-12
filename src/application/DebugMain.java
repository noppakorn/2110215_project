package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
//        TerrainGenerator terrainGenerator = new TerrainGenerator();
//        terrainGenerator.genTerrain();
        Group group = new Group();
        ImageView image = new ImageView(new Image(ClassLoader.getSystemResource("Coin.png").toString()));
        image.setFitWidth(20);
        image.setFitHeight(20);
        image.setX(500);
        image.setY(200);
//        group.getChildren().addAll(terrainGenerator.getEntities());
        group.getChildren().add(image);
        Scene scene = new Scene(group, 800, 600);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Poprio");
        stage.setResizable(false);
    }
}