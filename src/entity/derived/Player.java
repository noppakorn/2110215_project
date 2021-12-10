package entity.derived;

import entity.base.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Player extends Entity {
    private AnimationTimer animationTimer;
    private double y;
    private double x;
    private double vx;
    private double vy;
    private double ay;
    private double borderX;
    private double borderY;

    public Player() {
        this("Poprio");
    }

    public Player(String name) {
        /* Initialize player name, player stats , position and movement characteristics
        @param name: Name of the player
         */
        super(name);
        this.y = 50;
        this.x = 20;
        this.vx = 0;
        this.vy = 0;
        this.ay = 1;
        this.setX(x);
        this.setY(y);
        String imagePath = ClassLoader.getSystemResource("marioRight0Lvl0.png").toString();
        this.setImage(new Image(imagePath));
        this.setFocusTraversable(true);
        initializeMovement();
        setAnimationTimer();
        System.out.println("Player Initialized");
    }

    public void initializeMovement() {
        /* Set keyboard event handler for the player
         */
        this.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    x = Math.max(0, x - 5);
                    this.setX(x);
                }
                case RIGHT -> {
                    x = Math.min(500, x + 5);
                    this.setX(x);
                }
                case UP -> {
                    if (y == 260)
                        vy = 20;
                }
                case DOWN -> {
                    y += 10;
                    this.setY(y);
                }
                default -> System.out.println(keyEvent.getCode());
            }
        });
    }

    public void setAnimationTimer() {
        /* Set animation timer for the player movement
         */
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                y -= vy;
                Player.super.setY(y);
                if (y < 260) {
                    vy -= ay;
                }
                if (y == 260) {
                    vy = 0;
                }
            }

        };
        animationTimer.start();
    }

    public double getBorderX() {
        return borderX;
    }

    public void setBorderX(double borderX) {
        this.borderX = borderX;
    }

    public double getBorderY() {
        return borderY;
    }

    public void setBorderY(double borderY) {
        this.borderY = borderY;
    }
}
