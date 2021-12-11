package entity.derived;

import application.GameController;
import entity.base.Collectable;
import entity.base.Despawnable;
import entity.base.Entity;


/**
 * The type Coin.
 */
public class Coin extends Entity implements Collectable, Despawnable {
    private int point;
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
        this.point = value;
        this.setFitWidth(20);
        this.setFitHeight(20);
        initializeTexture("Coin");
        this.setX(x);
        this.setY(y);
        despawn = false;
    }

    @Override
    public void collect() {
        GameController.increasePoint(point);
        despawn = true;
    }

    public boolean isDespawn() {
        return despawn;
    }

    public String toString() {
        return "Coin valued " + point + " at (" + this.getX() + "," + this.getY() + ")";
    }
}
