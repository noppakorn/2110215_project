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

        this.upperBoundX = 700;
        this.lowerBoundX = 20;
        this.upperBoundY = 420;
        this.vy = 0;
        this.ay = 1;
        this.returnToBegin();
        this.goNextScene = false;

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
                    if (!move(30, 0)) {
                        goNextScene = true;
                    }
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


    /**
     * Move object using relative position.
     *
     * @param dx the value to add or remove to x.
     * @param dy the value to add or remove to y.
     * @return the result of the move operation
     */
    public boolean move(double dx, double dy) {
        return moveToPos(this.getX() + dx, this.getY() + dy);
    }

    /**
     * Move object using absolute position on screen with bound checking that position will not exceed bounds.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the result of the move operation
     */
    public boolean moveToPos(double x, double y) {
        if (x >= lowerBoundX && x <= upperBoundX && y <= upperBoundY) {
            this.setX(x);
            this.setY(y);
            return true;
        }
        return false;
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

}
