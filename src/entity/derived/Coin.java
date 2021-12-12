package entity.derived;

import application.GameController;
import entity.base.Collectable;
import entity.base.Despawnable;
import entity.base.Entity;


/**
 * The type Coin.
 */
public class Coin extends Entity implements Collectable, Despawnable {
    private int value;
    private boolean despawn;

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
        initializeTexture("Coin");
        despawn = false;
    }

    @Override
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
}
