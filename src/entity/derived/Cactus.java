package entity.derived;

import entity.base.Entity;
import entity.base.Solid;

public class Cactus extends Entity implements Solid {
    public Cactus() {
        super();
        initializeTexture("Cactus");

    }
    @Override
    public void blockPlayer(Entity e) {

    }
}
