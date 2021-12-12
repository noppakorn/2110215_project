package entity.derived;

import entity.base.Entity;

public class Box extends Entity {
    public Box(String name, double x, double y) {
        super(name, x, y);
        initializeTexture("Dirt");
        this.setX(x);
        this.setY(y);

    }


}
