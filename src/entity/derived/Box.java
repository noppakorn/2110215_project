package entity.derived;

import controller.GameController;
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
        this.setFitWidth(50);
        this.setFitHeight(50);
        this.setX(x);
        this.setY(y);
        initializeBlockTexture("Dirt");
    }

    /**
     * Initialize block texture.
     *
     * @param resourceName the resource name
     */
    public void initializeBlockTexture(String resourceName) {
        this.setImage(GameController.getTextureLoader().getBlockImage(resourceName));
    }


}
