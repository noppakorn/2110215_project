package entity.base;


import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {
    protected String name;
    protected int health;

    public Entity() {
        this("Anonymous");
    }
    public Entity(String name) {
        this(name, 100);
    }

    public Entity(String name, int health) {
        super();
        this.name = name;
        this.health = 100;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }
}
