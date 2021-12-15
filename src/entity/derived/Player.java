package entity.derived;

import controller.GameController;
import entity.base.Despawnable;
import entity.base.MoveableEntity;
import entity.base.Renderable;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * The type Player. This type represents the main player of the game;
 */
public class Player extends MoveableEntity implements Renderable, Despawnable {
    /**
     * Should the player be despawn
     */
    private boolean isDespawn;
    /**
     * Should the player be gone to next scene
     */
    private boolean isGoNextScene;
    /**
     * Indicate that the player is allowed to go left
     */
    private boolean isLeftEnabled;
    /**
     * Indicate that the player is allowed to go right
     */
    private boolean isRightEnabled;
    /**
     * Indicate that the player is allowed to go up
     */
    private boolean isUpEnabled;
    /**
     * Indicate that the player is allowed to go down
     */
    private boolean isDownEnabled;
    /**
     * Indicate that the player is jumping
     */
    private boolean isJumping;
    /**
     * Indicate that the player is moving right
     */
    private boolean isMovingRight;
    /**
     * Timer for the player animation
     */
    private int timer;

    /**
     * Instantiates a new Player with a default name Minerio.
     */
    public Player() {
        this("Minerio");
    }

    /**
     * Instantiates a new Player. Set up the proper texture and movement for the player
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

        isDespawn = false;
        isGoNextScene = false;
        isLeftEnabled = true;
        isRightEnabled = true;
        isUpEnabled = true;
        isDownEnabled = true;
        isJumping = false;
        isMovingRight = true;
        initializeMovement();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                Platform.runLater(() -> {
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
                    if (isLeftEnabled) {
                        this.velocityX = 5;
                        isMovingRight = false;
                    }
                }
                case RIGHT -> {
                    if (isRightEnabled) {
                        ++this.timer;
                        this.velocityX = -5;
                        isMovingRight = true;
                    }
                }
                case UP -> {
                    if (isUpEnabled && !isJumping) {
                        velocityY = 23;
                        isDownEnabled = true;
                        isJumping = true;
                        MediaPlayer jumpSound = new MediaPlayer(new Media(ClassLoader.getSystemResource("Jump.mp3").toString()));
                        jumpSound.setStartTime(Duration.millis(7));
                        jumpSound.play();
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
     * Check if the player is on the ground
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
            isDownEnabled = false;
            isJumping = false;
        } else if (isDownEnabled) {
            velocityY -= 1;
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
     * Animate the moving motion for the player.
     */
    public void animate() {
        if (velocityX == 0) {
            if (isMovingRight) initializeTexture("marioRight0");
            else initializeTexture("marioLeft0");
        } else if (isDownEnabled || isJumping) {
            if (isMovingRight) initializeTexture("marioRight4");
            else initializeTexture("marioLeft4");
        } else if (isMovingRight) {
            ++this.timer;
            if (timer >= 20) {
                timer = 0;
            }
            if (timer < 5) initializeTexture("marioRight0");
            else if (timer < 10) initializeTexture("marioRight1");
            else if (timer < 15) initializeTexture("marioRight2");
            else if (timer < 20) initializeTexture("marioRight3");
        } else {
            ++this.timer;
            if (timer >= 20) {
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
        return isGoNextScene;
    }

    /**
     * Sets state of the object to go to next scene.
     *
     * @param goNextScene the state of the object to go to next scene
     */
    public void setGoNextScene(boolean goNextScene) {
        this.isGoNextScene = goNextScene;
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
     * Kill the player.
     */
    public void killPlayer() {
        if (!GameController.DEBUG_ENABLED) {
            isDespawn = true;
            System.out.println("Player Killed");
        }
    }

    @Override
    public boolean isDespawn() {
        return isDespawn;
    }

    /**
     * Sets the x coordinates.
     *
     * @param x the x coordinates
     */
    public void setCorX(double x) {
        this.x = x;
    }

    /**
     * Sets the y coordinates.
     *
     * @param y the y coordinates
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
        return isRightEnabled;
    }

    /**
     * Sets right enabled.
     *
     * @param rightEnabled the right enabled
     */
    public void setRightEnabled(boolean rightEnabled) {
        this.isRightEnabled = rightEnabled;
    }

    /**
     * Gets left enabled.
     *
     * @return the left enabled
     */
    public boolean getLeftEnabled() {
        return isLeftEnabled;
    }

    /**
     * Sets left enabled.
     *
     * @param leftEnabled the left enabled
     */
    public void setLeftEnabled(boolean leftEnabled) {
        this.isLeftEnabled = leftEnabled;
    }

    /**
     * Check if the player is allowed to go up
     *
     * @return the boolean
     */
    public boolean isUpEnabled() {
        return isUpEnabled;
    }

    /**
     * Sets the allowed to go up status of the player
     *
     * @param upEnabled the up enabled
     */
    public void setUpEnabled(boolean upEnabled) {
        this.isUpEnabled = upEnabled;
    }

    /**
     * Is down enabled boolean.
     *
     * @return the boolean
     */
    public boolean isDownEnabled() {
        return isDownEnabled;
    }

    /**
     * Sets if the allowed to go down status of the player.
     *
     * @param downEnabled the down enabled
     */
    public void setDownEnabled(boolean downEnabled) {
        this.isDownEnabled = downEnabled;
    }

    /**
     * Sets if the player is jumping
     *
     * @param jumping the jumping state
     */
    public void setJumping(boolean jumping) {
        this.isJumping = jumping;
    }
}
