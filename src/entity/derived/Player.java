package entity.derived;

import controller.GameController;
import entity.base.MoveableEntity;
import entity.base.Renderable;
import entity.base.Despawnable;
import javafx.animation.AnimationTimer;

/**
 * The type Player. This type represents the main player of the game;
 */
public class Player extends MoveableEntity implements Renderable, Despawnable {
    private boolean despawn;
    private boolean goNextScene;
    private double accelationY;
    public boolean rightOverride;
    public boolean leftOverride;

    /**
     * Instantiates a new Player with a default name Minerio.
     */
    public Player() {
        this("Minerio");
    }

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public Player(String name) {
        super(name);
        despawn = false;
        goNextScene = false;
        initializeTexture("Player");
        this.setFocusTraversable(true);
        this.velocityY = 0;
        this.velocityX = 0;
        this.accelationY = 1;
        this.returnToBegin();
        this.width = 50;
        this.height = 50;
        this.setFitHeight(width);
        this.setFitWidth(height);
        this.x = lowerBoundX;
        this.y = upperBoundY;
        this.rightOverride = false;
        this.leftOverride = false;

        initializeMovement();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                if (Player.super.getY() < upperBoundY) {
                    velocityY -= accelationY;
                }
                if (Player.super.getY() > upperBoundY) {
                    velocityY = 0;
                }
            }

        };
        animationTimer.start();
    }

    /**
     * Initialize movement event handler and gravity for player.
     */
    public void initializeMovement() {
        this.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    this.velocityX = 5;
                    rightOverride = false;
                    leftOverride = true;
                }
                case RIGHT -> {
                    this.velocityX = -5;
                    leftOverride = false;
                    rightOverride = true;
                }
                case UP -> {
                    if (isOnTheGround()) {
                        velocityY = 20;
                    }
                }
                case DOWN -> {
                }
            }
        });
        this.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    this.velocityX = 0;
                    leftOverride = false;
                }
                case RIGHT -> {
                    this.velocityX = 0;
                    rightOverride = false;
                }
            }
        });
    }

    /**
     * Move player to the beginning of the scene.
     */
    public void returnToBegin() {
        this.setX(lowerBoundX);
    }

    /**
     * Is on the ground boolean.
     *
     * @return the boolean
     */
    public boolean isOnTheGround() {
        return !(y < upperBoundY);
    }

    @Override
    public void update() {
        if (this.x - velocityX > sceneUpperBoundX) {
            this.setGoNextScene(true);
            this.x = lowerBoundX;
        } else if (this.x - velocityX < lowerBoundX) {
            this.x = lowerBoundX;
        } else {
            this.x -= velocityX;
        }
        if (this.y - velocityY > upperBoundY) {
            this.y = upperBoundY;
            velocityY = 0;
        } else {
            this.y -= velocityY;
        }
        this.setX(x);
        this.setY(y);
    }


    @Override
    public String toString() {
        return super.toString() + " at (" + this.getX() + "," + this.getY() + ")";
    }

    /**
     * State of the object if the object should go to the next scene
     *
     * @return the boolean
     */
    public boolean isGoNextScene() {
        return goNextScene;
    }

    /**
     * Sets state of the object to go to next scene.
     *
     * @param goNextScene the state of the object to go to next scene
     */
    public void setGoNextScene(boolean goNextScene) {
        this.goNextScene = goNextScene;
    }

    /**
     * Check if the player is falling
     *
     * @return the boolean representing the falling status of the player
     */
    public boolean isFalling() {
        return velocityY < 0;
    }

    /**
     * Kill player.
     */
    public void killPlayer() {
        if (!GameController.debugEnabled) {
            despawn = true;
            System.out.println("Player Killed");
        }
    }

    @Override
    public boolean isDespawn() {
        return despawn;
    }

    public void setCorX(double x) {
        this.x = x;
    }

    public void setCorY(double y) {
        this.y = y;
    }

}
