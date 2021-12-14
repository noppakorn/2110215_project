package entity.derived;

import controller.GameController;
import controller.TextureLoader;
import entity.base.MoveableEntity;
import entity.base.Renderable;
import entity.base.Despawnable;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

/**
 * The type Player. This type represents the main player of the game;
 */
public class Player extends MoveableEntity implements Renderable, Despawnable {
    private final double accelerationY = 1;
    private boolean despawn;
    private boolean goNextScene;
    private boolean leftEnabled;
    private boolean rightEnabled;
    private boolean upEnabled;
    private boolean downEnabled;
    private boolean jumping;
    private boolean movingRight;
    private int timer;
    /**
     * Instantiates a new Player with a default name Minerio.
     */
    public Player() {
        this("Minerio");
    }

    /**
     * Instantiates a new Player.
     *
     * @param name the name of the Player.
     */
    public Player(String name) {
        super(name);
        this.setFocusTraversable(true);
        this.velocityY = 0;
        this.velocityX = 0;
        this.returnToBegin();
        this.setFitHeight(50);
        this.setFitWidth(50);
        initializeTexture("marioRight0");
        this.timer = 0;

        this.x = lowerBoundX;
        this.y = upperBoundY;

        despawn = false;
        goNextScene = false;
        leftEnabled = true;
        rightEnabled = true;
        upEnabled = true;
        downEnabled = true;
        jumping = false;
        movingRight = true;

        initializeMovement();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                Platform.runLater(()->{
                    animate();
                });
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
                    if (leftEnabled) {
                        this.velocityX = 5;
                        movingRight = false;
                    }
                }
                case RIGHT -> {
                    if (rightEnabled) {
                        ++this.timer;
                        this.velocityX = -5;
                        movingRight = true;
                    }
                }
                case UP -> {
                    if (upEnabled && !jumping) {
                        velocityY = 20;
                        downEnabled = true;
                        jumping = true;
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
                    setRightEnabled(true);
                }
                case RIGHT -> {
                    this.velocityX = 0;
                    setLeftEnabled(true);
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
        return y >= upperBoundY;
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
        if (this.y - velocityY >= upperBoundY) {
            this.y = upperBoundY;
            velocityY = 0;
            downEnabled = false;
            jumping = false;
        } else if (downEnabled) {
            velocityY -= accelerationY;
            this.y -= velocityY;
        }
        this.setX(x);
        this.setY(y);
    }


    @Override
    public String toString() {
        return super.toString() + " at (" + this.getX() + "," + this.getY() + ")";
    }
    public void animate(){
        if (velocityX == 0){
            System.out.println(movingRight);
            if (movingRight) initializeTexture("marioRight0");
            else initializeTexture("marioLeft0");
        }
        else if (downEnabled || jumping){
            if (movingRight) initializeTexture("marioRight4");
            else initializeTexture("marioLeft4");
        }
        else if (movingRight) {
            ++this.timer;
            if (timer == 20){
                timer = 0;
            }
            if (timer < 5) initializeTexture("marioRight0");
            else if (timer < 10) initializeTexture("marioRight1");
            else if (timer < 15) initializeTexture("marioRight2");
            else if (timer < 20) initializeTexture("marioRight3");
        }
        else {
            ++this.timer;
            if (timer == 20){
                timer = 0;
            }
            if (timer < 5) initializeTexture("marioLeft0");
            else if (timer < 10) initializeTexture("marioLeft1");
            else if (timer < 15) initializeTexture("marioLeft2");
            else if (timer < 20) initializeTexture("marioLeft3");
        }

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

    /**
     * Sets cor x.
     *
     * @param x the x
     */
    public void setCorX(double x) {
        this.x = x;
    }

    /**
     * Sets cor y.
     *
     * @param y the y
     */
    public void setCorY(double y) {
        this.y = y;
    }

    /**
     * Gets right enabled.
     *
     * @return the right enabled
     */
    public boolean getRightEnabled() {
        return rightEnabled;
    }

    /**
     * Sets right enabled.
     *
     * @param rightEnabled the right enabled
     */
    public void setRightEnabled(boolean rightEnabled) {
        this.rightEnabled = rightEnabled;
    }

    /**
     * Gets left enabled.
     *
     * @return the left enabled
     */
    public boolean getLeftEnabled() {
        return leftEnabled;
    }

    public boolean isUpEnabled() {
        return upEnabled;
    }

    public void setUpEnabled(boolean upEnabled) {
        this.upEnabled = upEnabled;
    }

    /**
     * Sets left enabled.
     *
     * @param leftEnabled the left enabled
     */
    public void setLeftEnabled(boolean leftEnabled) {
        this.leftEnabled = leftEnabled;
    }

    public boolean isDownEnabled() {
        return downEnabled;
    }

    public void setDownEnabled(boolean downEnabled) {
        this.downEnabled = downEnabled;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
