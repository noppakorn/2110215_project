package entity.base;

import javafx.geometry.Point2D;

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

    public void setUpperBoundX(double upperBoundX) {
        this.upperBoundX = upperBoundX;
    }

    public void setUpperBoundY(double upperBoundY) {
        this.upperBoundY = upperBoundY;
    }

    public void setLowerBoundX(double lowerBoundX) {
        this.lowerBoundX = lowerBoundX;
    }

    public void setVelocityX(double velocityX){
        this.velocityX = velocityX;
    }
    public void setVelocityY(double velocityY){
         this.velocityY = velocityY;
    }
    public double getVelocityY(){
        return velocityY;
    }
    public double getVelocityX(){
        return velocityX;
    }
}
