package scene;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Menu extends VBox {
    Text title;
    Label label;
    public boolean gameStart;

    public Menu() {
        super();
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
        title = new Text("Poprio");
        title.setStyle("-fx-font-weight: bold");
        title.setFont(new Font("Arial", 72));
        initializeStartText();
        gameStart = false;
        this.setOnMouseClicked(event -> {
            gameStart = true;
        });
        this.getChildren().addAll(title, label);
    }

    public void initializeStartText() {
        label = new Label("Click anywhere to start game.");
        label.setFont(new Font("Arial", 20));
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }
}
