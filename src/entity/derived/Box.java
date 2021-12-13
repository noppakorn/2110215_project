package entity.derived;

import entity.base.Entity;

/**
 * The type Box.
 */
public class Box extends Entity {
    /**
     * Instantiates a new Box.
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     */
    public Box(String name, double x, double y) {
        super(name, x, y);
        this.width = 30;
        this.height = 30;
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setX(x);
        this.setY(y);
    }


}
