package entity.base;

/**
 * The interface Despawnable. Indicate that the entity can be despawn
 */
public interface Despawnable {
    /**
     * Check if the entity should be despawn
     *
     * @return the boolean indicting if the entity should be despawn
     */
    boolean isDespawn();
}
