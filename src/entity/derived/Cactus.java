package entity.derived;

import controller.GameController;
import entity.base.Entity;
import entity.base.Solid;

public class Cactus extends Entity implements Solid {
    public Cactus(String name, int x, int y) {
        super(name, x, y);
        this.setFitHeight(50);
        this.setFitWidth(50);
        this.setX(x);
       this.setY(y);

        initializeTexture("Cactus");
    }
    @Override
    public void blockPlayer(Entity e) {

    }

    @Override
    public void initializeTexture(String resourceName) {
        this.setImage(GameController.getTextureLoader().getBlockImage(resourceName));
    }
}
