package entity.base;

/**
 * The type Moveable entity.
 */
public abstract class MoveableEntity extends Entity {
    /**
     * The Velocity y.
     */
    protected double velocityY;
    /**
     * The Velocity x.
     */
    protected double velocityX;
    /**
     * The Scene upper bound x.
     */
    protected double sceneUpperBoundX = 700;
    /**
     * The maximum x coordinates the entity is allowed
     */
    protected double upperBoundX = 700;
    /**
     * The maximum y coordinates the entity is allowed
     */
    protected double upperBoundY = 400;
    /**
     * The minimum x coordinates the entity is allowed
     */
    protected double lowerBoundX = 20;

    /**
     * Instantiates a new Moveable entity.
     *
     * @param name the name
     */
    public MoveableEntity(String name) {
        super(name);
    }

    /**
     * Instantiates a new Moveable entity.
     *
     * @param name the name
     * @param x    the x coordinates
     * @param y    the y coordinates
     */
    public MoveableEntity(String name, double x, double y) {
        super(name, x, y);
    }

    /**
     * Get velocity y.
     *
     * @return the double
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Set velocity y.
     *
     * @param velocityY the velocity y
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Set velocity x.
     *
     * @param velocityX the velocity x
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
}
