package entity.base;


import controller.GameController;
import javafx.scene.image.ImageView;

/**
 * The type Entity. A base class for all the entity in the game.
 */
public abstract class Entity extends ImageView {
    /**
     * The X coordinates.
     */
    protected double x;
    /**
     * The Y coordinates.
     */
    protected double y;
    /**
     * The Name of the entity.
     */
    protected String name;

    /**
     * Instantiates a new Anonymous Entity.
     */
    public Entity() {
        this("Anonymous");
    }

    /**
     * Instantiates a new Entity at (0,0).
     *
     * @param name the name of the entity
     */
    public Entity(String name) {
        this(name, 0, 0);
    }

    /**
     * Instantiates a new Entity.
     *
     * @param name the name of the entity
     * @param x    the x coordinate
     * @param y    the y coordinate
     */
    public Entity(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return this.getClass() + ": " + name;
    }

    /**
     * Gets name of the entity.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * Initialize texture.
     *
     * @param resourceName the resource path
     */
    public void initializeTexture(String resourceName) {
        this.setImage(GameController.getTextureLoader().getImage(resourceName));
    }

}
