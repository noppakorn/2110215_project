package entity.derived;

import controller.GameController;

/**
 * The type Cactus.
 */
public class Cactus extends Box {
    /**
     * Instantiates a new Cactus.
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     */
    public Cactus(String name, double x, double y) {
        super(name, x, y);
        this.setFitHeight(50);
        this.setFitWidth(50);
        this.setX(x);
        this.setY(y);

        initializeTexture("Cactus");
    }

    @Override
    public void initializeTexture(String resourceName) {
        this.setImage(GameController.getTextureLoader().getBlockImage(resourceName));
    }
}
