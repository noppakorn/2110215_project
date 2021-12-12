package scene;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameOver extends VBox {
    private Text title;
    public GameOver() {
        super();
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));

        title = new Text("Game Over!");
        title.setStyle("-fx-font-weight: bold");
        title.setFont(new Font("Arial", 72));
        title.setTextAlignment(TextAlignment.CENTER);
        this.getChildren().add(title);
    }
}
