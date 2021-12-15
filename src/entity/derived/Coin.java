package entity.derived;

import controller.GameController;
import entity.base.Despawnable;
import entity.base.Entity;
import javafx.animation.AnimationTimer;


/**
 * The type Coin.
 */
public class Coin extends Entity implements Despawnable {
    /**
     * The Money and Points value.
     */
    protected int value;
    /**
     * The value indicated should the object be despawn
     */
    protected boolean isDespawn;
    /**
     * The timer for controlling the animation
     */
    private int timer;

    /**
     * Instantiates a new Coin with the defaults values
     */
    public Coin() {
        this(1, 0, 0);
    }

    /**
     * Instantiates a new Coin.
     *
     * @param x the x
     * @param y the y
     */
    public Coin(double x, double y) {
        this(1, x, y);
    }

    /**
     * Instantiates a new Coin.
     *
     * @param value the value
     * @param x     the x
     * @param y     the y
     */
    public Coin(int value, double x, double y) {
        super("Coin");
        this.value = value;
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.setX(x);
        this.setY(y);
        this.timer = 0;
        isDespawn = false;
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animate();
                setImage();
            }

        };
        animationTimer.start();
    }

    /**
     * Do actions when the coin is collected by the player.
     */
    public void collect() {
        GameController.increasePoint(value);
        GameController.increaseMoney(value);
        isDespawn = true;
    }

    @Override
    public boolean isDespawn() {
        return isDespawn;
    }

    @Override
    public String toString() {
        return super.toString() + " valued " + value + " at (" + this.getX() + "," + this.getY() + ")";
    }

    /**
     * Sets the image of the coin.
     */
    public void setImage() {
        if (timer < 28) initializeTexture("coin0");
        else if (timer < 37) initializeTexture("coin1");
        else initializeTexture("coin2");
    }

    /**
     * Handle the coin animation.
     */
    public void animate() {
        timer++;
        if (timer == 50) {
            timer = 0;
        }
    }

}
