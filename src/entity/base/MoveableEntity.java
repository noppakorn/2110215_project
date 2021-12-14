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
     * The Upper bound x.
     */
    protected double upperBoundX = 700;
    /**
     * The Upper bound y.
     */
    protected double upperBoundY = 400;
    /**
     * The Lower bound x.
     */
    protected double lowerBoundX = 20;

    /**
     * Instantiates a new Moveable entity.
     */
    public MoveableEntity() {
        super();
    }

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
     * @param x    the x
     * @param y    the y
     */
    public MoveableEntity(String name, double x, double y) {
        super(name, x, y);
    }

    /**
     * Sets upper bound x.
     *
     * @param upperBoundX the upper bound x
     */
    public void setUpperBoundX(double upperBoundX) {
        this.upperBoundX = upperBoundX;
    }

    /**
     * Sets upper bound y.
     *
     * @param upperBoundY the upper bound y
     */
    public void setUpperBoundY(double upperBoundY) {
        this.upperBoundY = upperBoundY;
    }

    /**
     * Sets lower bound x.
     *
     * @param lowerBoundX the lower bound x
     */
    public void setLowerBoundX(double lowerBoundX) {
        this.lowerBoundX = lowerBoundX;
    }

    /**
     * Get velocity y double.
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
     * Get velocity x double.
     *
     * @return the double
     */
    public double getVelocityX() {
        return velocityX;
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
