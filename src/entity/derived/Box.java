package entity.derived;

import entity.base.Entity;
import controller.GameController;

public class Box extends Entity {
    public Box(String name, double x, double y) {
        super(name, x, y);
        this.width = 30;
        this.height = 30;
        this.setFitWidth(width);
        this.setFitHeight(height);
        this.setX(x);
        this.setY(y);
    }


}
