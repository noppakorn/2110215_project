package entity.derived;

import entity.base.Attackable;
import entity.base.Entity;
import entity.base.MoveableEntity;
import entity.base.Renderable;
import javafx.animation.AnimationTimer;

/**
 * The type Player. This type represents the main player of the game;
 */
public class Player extends MoveableEntity implements Attackable, Renderable {
    private boolean goNextScene;
    //    private double vx;
    private double accelationY;
    private boolean isOnTheGround;

    /**
     * Instantiates a new Player with a default name Poprio.
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
        initializeTexture("Player");
        this.setFocusTraversable(true);
        this.velocityY = 0;
        this.velocityX = 0;
        this.accelationY = 1;
        this.returnToBegin();
        this.goNextScene = false;
        this.setFitHeight(50);
        this.setFitWidth(50);
        this.x = lowerBoundX;
        this.y = upperBoundY;

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

        System.out.println("Player: " + this + " initialized");
    }

    /**
     * Initialize movement event handler and gravity for player.
     */
    public void initializeMovement() {
        this.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    this.velocityX = 5;
                }
                case RIGHT -> {
                    this.velocityX = -5;
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
    }
    /**
     * Move player to the beginning of the scene.
     */
    public void returnToBegin() {
        this.setX(lowerBoundX);
    }

    public boolean isOnTheGround() {
        return !(y < upperBoundY);
    }

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
        } else {
            this.y -= velocityY;
        }
        this.setX(x);
        this.setY(y);
    }
//    @Override
//    public void moveToPos(double x, double y) {
//        if (x >= lowerBoundX && x <= upperBoundX && y <= upperBoundY) {
//            this.setX(x);
//            this.setY(y);
//        } else if (x >= sceneUpperBoundX) {
//            this.setGoNextScene(true);
//        }
//    }

    @Override
    public boolean attack(Entity e) {
        return false;
    }

    public String toString() {
        return "Player: " + name + " at (" + this.getX() + "," + this.getY() + ")";
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
     * Gets scene upper bound x.
     *
     * @return the scene upper bound x
     */
    public double getSceneUpperBoundX() {
        return sceneUpperBoundX;
    }

    /**
     * Sets scene upper bound x.
     *
     * @param sceneUpperBoundX the scene upper bound x
     */

    /**
     * Gets upper bound x.
     *
     * @return the upper bound x
     */
    public double getUpperBoundX() {
        return upperBoundX;
    }


    /**
     * Gets upper bound y.
     *
     * @return the upper bound y
     */
    public double getUpperBoundY() {
        return upperBoundY;
    }


    /**
     * Gets lower bound x.
     *
     * @return the lower bound x
     */
    public double getLowerBoundX() {
        return lowerBoundX;
    }

}
