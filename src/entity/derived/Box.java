package entity.derived;

import entity.base.Entity;

public class Box extends Entity {
    public Box(String name, double x, double y) {
        super(name, x, y);
        this.setFitWidth(30);
        this.setFitHeight(30);
        this.setX(x);
        this.setY(y);
        initializeTexture("Coin");
    }


}
