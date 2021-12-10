package entity.base;

/**
 * The interface Attackable. Class which implements this method can attack other entities.
 */
public interface Attackable {
    /**
     * Attack another entity.
     *
     * @param e the e
     * @return the boolean
     */
    boolean attack(Entity e);
}
