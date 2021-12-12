package entity.derived;

import entity.base.Collectable;
import entity.base.MoveableEntity;

/**
 * The type Booster.
 */
public class Booster extends MoveableEntity implements Collectable {
    /**
     * Instantiates a new Moveable entity.
     *
     * @param name the name
     */
    public Booster(String name) {
        super(name);
    }

    @Override
    public void collect() {

    }
}
