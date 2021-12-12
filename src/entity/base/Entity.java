package entity.base;


import application.GameController;
import javafx.scene.image.ImageView;

/**
 * The type Entity. A base class for all the entity in the game.
 */
public abstract class Entity extends ImageView {
    /**
     * The X.
     */
    protected double x, /**
     * The Y.
     */
    y;
//    protected boolean destroyed;
//    protected boolean isImortal;
    /**
     * The Name.
     */
    protected String name;

    /**
     * Instantiates a new Entity.
     */
    public Entity() {
        this("Anonymous");
    }

    /**
     * Instantiates a new Entity.
     *
     * @param name the name
     */
    public Entity(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Name: " + name;
    }

    /**
     * Gets name.
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

    /**
     * Move object using absolute position on screen with bound checking that position will not exceed bounds.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void moveToPos(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
}
