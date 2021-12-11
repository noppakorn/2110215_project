package entity.base;


import javafx.scene.image.Image;
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
     * @param resourcePath the resource path
     */
    public void initializeTexture(String resourcePath)  {
        this.setImage(new Image(ClassLoader.getSystemResource(resourcePath).toString()));
    }

}
