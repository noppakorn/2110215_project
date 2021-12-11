package entity.derived;

import entity.base.Attackable;
import entity.base.Despawnable;
import entity.base.Entity;

/**
 * The type Enemy.
 */
public class Enemy extends Entity implements Attackable, Despawnable {
    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     */
    private double upperBoundX;
    private double upperBoundY;
    private double lowerBoundX;
    private boolean despawn;

    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     */
    public Enemy(String name) {
        super(name);
        despawn = false;
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

    @Override
    public boolean isDespawn() {
        return despawn;
    }
}
