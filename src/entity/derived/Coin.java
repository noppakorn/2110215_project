package entity.derived;

import application.GameController;
import entity.base.Collectable;
import entity.base.Entity;

/**
 * The type Coin.
 */
public class Coin extends Entity implements Collectable {
    private int point;

    /**
     * Instantiates a new Coin.
     */
    public Coin() {
        this(1);
    }

    /**
     * Instantiates a new Coin.
     *
     * @param value the value
     */
    public Coin(int value) {
        super("Coin");
        this.point = value;
    }

    @Override
    public void collect() {
        GameController.increasePoint(point);

    }

    /**
     * Despawn.
     */
    public void despawn() {

    }

}
