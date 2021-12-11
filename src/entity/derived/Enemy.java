package entity.derived;

import entity.base.Attackable;
import entity.base.Entity;

/**
 * The type Enemy.
 */
public class Enemy extends Entity implements Attackable {
    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     */
    private double upperBoundX;
    private double upperBoundY;
    private double lowerBoundX;

    public Enemy(String name) {
        super(name);
    }

    @Override
    public void moveToPos(double x, double y) {
        if (x >= lowerBoundX && x <= upperBoundX) {
            super.moveToPos(x, 0);
        }
    }

    @Override
    public boolean attack(Entity e) {
        return false;
    }
}
