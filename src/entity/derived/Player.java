package entity.derived;

import entity.base.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 * The type Player. This type represents the main player of the game;
 */
public class Player extends Entity {
    private AnimationTimer animationTimer;
    private double posY;
    private double posX;
    private double vx;
    private double vy;
    private double ay;
    private double borderX;
    private double borderY;
    private boolean goNextScene;

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
        this.posY = 260;
        this.posX = 20;
        this.vx = 0;
        this.vy = 0;
        this.ay = 1;
        setBorderX(700);
        setBorderY(500);
        this.setX(posX);
        this.setY(posY);
        this.goNextScene = false;
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
                    posX = Math.max(0, posX - 5);
                    this.setX(posX);
                }
                case RIGHT -> {
                    if (posX < borderX) {
                        posX += 5;
                        this.setX(posX);
                    } else {
                        goNextScene = true;
                        System.out.println(goNextScene);
                    }
                }
                case UP -> {
                    if (posY == 260)
                        vy = 20;
                }
                case DOWN -> {
                    posY += 10;
                    this.setY(posY);
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
                posY -= vy;
                Player.super.setY(posY);
                if (posY < 260) {
                    vy -= ay;
                }
                if (posY == 260) {
                    vy = 0;
                }
            }

        };
        animationTimer.start();
    }
    public void returnToBegin() {
        this.posY = 260;
        this.posX = 20;
        this.setY(posY);
        this.setX(posX);
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

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public boolean isGoNextScene() {
        return goNextScene;
    }

    public void setGoNextScene(boolean goNextScene) {
        this.goNextScene = goNextScene;
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
