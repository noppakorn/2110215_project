package application;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class gametest {
    private AnimationTimer animationTimer;
    private long lastTimeTriggered;
    public Rectangle poprio;
    public double y = 260;
    private double x = 30;

    public void createGame(Group gameDisplay) {
        Rectangle background = new Rectangle(0, 0, 800, 600);
        background.setFill(Color.LIGHTSKYBLUE);
        Canvas game = new Canvas(800, 600);
        poprio = new Rectangle(x, y, 20, 70);
        poprio.setOnKeyPressed(movement);
        poprio.setFocusTraversable(true);
        poprio.setFill(Color.GRAY);
        gameDisplay.getChildren().addAll(background, game, poprio);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (y < 260) {
                    y++;
                    poprio.setY(y);
                }
            }

        };
        animationTimer.start();
    }

    private EventHandler<KeyEvent> movement = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            switch (keyEvent.getCode()) {
                case A -> {
                    x = Math.max(0, x - 5);
                    poprio.setX(x);
                }
                case D -> {
                    x = Math.min(500, x + 5);
                    poprio.setX(x);
                }
                case W -> {
                    y -= 200;
                    poprio.setY(y);
                }
                case S -> {
                    y += 10;
                    poprio.setY(y);
                }
            }
        }
    };
}
