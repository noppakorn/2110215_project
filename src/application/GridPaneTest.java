package application;


import block.Block;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GridPaneTest extends Application {
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setMinHeight(600);
        root.setMaxHeight(600);
        root.setMinWidth(800);
        root.setMaxWidth(800);
        for (int i = 0; i < 12; ++i) {
           for (int j = 0; j < 16; ++j) {
               Pane tmpPane = new Pane();
               tmpPane.setMaxHeight(50);
               tmpPane.setMaxWidth(50);
               tmpPane.setMinHeight(50);
               tmpPane.setMinWidth(50);
               root.add(tmpPane, i, j);
           }
        }
        for (int i = 9; i < 12; ++i) {
            for (int j = 0; j < 16; ++j) {
                Block block = new Block();
                root.add(block, j, i);
            }
        }
        System.out.println(root.getChildren().toString());
        Scene scene = new Scene(root, 800, 600, Color.LIGHTSKYBLUE);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
