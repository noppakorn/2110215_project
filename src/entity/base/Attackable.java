package entity.base;

/**
 * The interface Attackable. Class which implements this method can attack other entities.
 */
public interface Attackable {
    /**
     * Do attack action to another entity.
     *
     * @param e the entity to be attacked.
     */
    void attack(Entity e);
}