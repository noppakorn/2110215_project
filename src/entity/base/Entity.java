package entity.base;


import application.GameController;
import javafx.scene.image.ImageView;

/**
 * The type Entity. A base class for all of the entity in the game.
 */
public abstract class Entity extends ImageView {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Health.
     */
    protected int health;

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
        this(name, 100);
    }

    /**
     * Instantiates a new Entity.
     *
     * @param name   the name
     * @param health the health
     */
    public Entity(String name, int health) {
        super();
        this.name = name;
        this.health = 100;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health) {
        this.health = Math.max(0, health);
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
