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
     * The Value.
     */
    protected int value;
    /**
     * The Despawn.
     */
    protected boolean despawn;
    private int timer;

    /**
     * Instantiates a new Coin.
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
    public Coin(int x, int y) {
        this(1, x, y);
    }

    /**
     * Instantiates a new Coin.
     *
     * @param value the value
     * @param x     the x
     * @param y     the y
     */
    public Coin(int value, int x, int y) {
        super("Coin");
        this.value = value;
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.setX(x);
        this.setY(y);
        this.timer = 0;
        despawn = false;
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animate();
                setImage();
            }

        };
        animationTimer.start();
    }

    public void collect() {
        GameController.increasePoint(value);
        GameController.increaseMoney(value);
        despawn = true;
    }

    public boolean isDespawn() {
        return despawn;
    }

    @Override
    public String toString() {
        return super.toString() + " valued " + value + " at (" + this.getX() + "," + this.getY() + ")";
    }

    /**
     * Sets image.
     */
    public void setImage() {
        if (timer < 28) initializeTexture("coin0");
        else if (timer < 37) initializeTexture("coin1");
        else initializeTexture("coin2");
    }

    /**
     * Animate.
     */
    public void animate() {
        timer++;
        if (timer == 50) {
            timer = 0;
        }
    }

}
