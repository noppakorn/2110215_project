package entity.derived;

import entity.base.Attackable;
import entity.base.Entity;

/**
 * The type Enemy.
 */
public class Enemy extends Entity implements Attackable {
    @Override
    public boolean attack(Entity e) {
        return false;
    }
}
