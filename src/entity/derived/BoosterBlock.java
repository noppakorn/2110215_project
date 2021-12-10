package entity.derived;

import entity.base.Entity;
import entity.base.Movable;

public class BoosterBlock extends Entity implements Movable {
    public BoosterBlock() {

    }

    @Override
    public boolean move(int dx, int dy) {
        this.setX(this.getX() + 10);
        this.setY(this.getY() + 10);
        return true;
    }
}
