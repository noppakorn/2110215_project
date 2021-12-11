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
    public Enemy(String name) {
        super(name);
    }
    @Override
    public boolean attack(Entity e) {
        return false;
    }
}
