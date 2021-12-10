package entity.base;


import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {
    protected String name;
    protected int health;

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
