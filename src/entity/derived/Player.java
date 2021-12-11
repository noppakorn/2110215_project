package entity.derived;

import entity.base.Attackable;
import entity.base.Entity;
import javafx.animation.AnimationTimer;

/**
 * The type Player. This type represents the main player of the game;
 */
public class Player extends Entity implements Attackable {
    private boolean goNextScene;
    //    private double vx;
    private double vy;
    private double ay;
    private double sceneUpperBoundX;
    private double upperBoundX;
    private double upperBoundY;
    private double lowerBoundX;

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
        initializeTexture("marioRight0Lvl0.png");
        this.setFocusTraversable(true);

        this.sceneUpperBoundX = 700;
        this.upperBoundX = 700;
        this.lowerBoundX = 20;
        this.upperBoundY = 400;
        this.vy = 0;
        this.ay = 1;
        this.returnToBegin();
        this.goNextScene = false;
        this.setFitHeight(50);
        this.setFitWidth(50);

        initializeMovement();

        System.out.println("Player: " + this + " initialized");
    }

    /**
     * Initialize movement event handler and gravity for player.
     */
    public void initializeMovement() {
        this.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> {
                    move(-5, 0);
                }
                case RIGHT -> {
                    move(30, 0);
                }
                case UP -> {
                    if (this.getY() >= upperBoundY)
                        vy = 20;
                }
                case DOWN -> {
                    if (this.getY() + 10 <= upperBoundY) {
                        move(0, 10);
                    }
                }
            }
        });
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move(0, -vy);
                if (Player.super.getY() < upperBoundY) {
                    vy -= ay;
                }
                if (Player.super.getY() > upperBoundY) {
                    vy = 0;
                }
            }

        };
        animationTimer.start();
    }


    /**
     * Move player to the beginning of the scene.
     */
    public void returnToBegin() {
        this.setX(lowerBoundX);
        this.setY(upperBoundY);
    }

    public void move(double dx, double dy) {
        moveToPos(this.getX() + dx, this.getY() + dy);
    }

    @Override
    public void moveToPos(double x, double y) {
        if (x >= lowerBoundX && x <= upperBoundX && y <= upperBoundY) {
            this.setX(x);
            this.setY(y);
        } else if (x >= sceneUpperBoundX) {
            this.setGoNextScene(true);
        }
    }

    @Override
    public boolean attack(Entity e) {
        return false;
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
    public void setSceneUpperBoundX(double sceneUpperBoundX) {
        this.sceneUpperBoundX = sceneUpperBoundX;
    }

    /**
     * Gets upper bound x.
     *
     * @return the upper bound x
     */
    public double getUpperBoundX() {
        return upperBoundX;
    }

    /**
     * Sets upper bound x.
     *
     * @param upperBoundX the upper bound x
     */
    public void setUpperBoundX(double upperBoundX) {
        this.upperBoundX = upperBoundX;
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
     * Sets upper bound y.
     *
     * @param upperBoundY the upper bound y
     */
    public void setUpperBoundY(double upperBoundY) {
        this.upperBoundY = upperBoundY;
    }

    /**
     * Gets lower bound x.
     *
     * @return the lower bound x
     */
    public double getLowerBoundX() {
        return lowerBoundX;
    }

    /**
     * Sets lower bound x.
     *
     * @param lowerBoundX the lower bound x
     */
    public void setLowerBoundX(double lowerBoundX) {
        this.lowerBoundX = lowerBoundX;
    }
}
