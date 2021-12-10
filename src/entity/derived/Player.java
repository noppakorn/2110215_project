package entity.derived;

import entity.base.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 * The type Player. This type represents the main player of the game;
 */
public class Player extends Entity {
    private AnimationTimer animationTimer;
    private double y;
    private double x;
    private double vx;
    private double vy;
    private double ay;
    private double borderX;
    private double borderY;

    /**
     * Instantiates a new Player.
     */
    public Player() {
        this("Poprio");
    }

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public Player(String name) {
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

    /**
     * Initialize movement.
     */
    public void initializeMovement() {
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

    /**
     * Set keyboard event handler for the player
     */
    public void setAnimationTimer() {
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

    /**
     * Set animation timer for the player movement
     *
     * @return the border x
     */
    public double getBorderX() {
        return borderX;
    }

    /**
     * Sets border x.
     *
     * @param borderX the border x
     */
    public void setBorderX(double borderX) {
        this.borderX = borderX;
    }

    /**
     * Gets border y.
     *
     * @return the border y
     */
    public double getBorderY() {
        return borderY;
    }

    /**
     * Sets border y.
     *
     * @param borderY the border y
     */
    public void setBorderY(double borderY) {
        this.borderY = borderY;
    }
}
