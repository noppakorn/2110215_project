package entity.derived;

import entity.base.Attackable;
import entity.base.Entity;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Player extends Entity {
    private AnimationTimer animationTimer;
    private long lastTimeTriggered;
    private double y = 260;
    private double x = 30;
    private double vx = 0;
    private double vy = 0;
    private double ay = 1;
    private EventHandler<KeyEvent> movement;

    public Player() {
        this("Poprio");
    }
    public Player(String name) {
        this.name = name;
        initializeMovement();
    }
    public void initializeMovement() {
        movement = keyEvent -> {
            switch (keyEvent.getCode()) {
                case A -> {
                    x = Math.max(0, x - 5);
                    this.setX(x);
                }
                case D -> {
                    x = Math.min(500, x + 5);
                    this.setX(x);
                }
                case W -> {
                    if (y == 260)
                        vy = 20;
                }
                case S -> {
                    y += 10;
                    this.setY(y);
                }
            }
        };
    }

}
