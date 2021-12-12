package entity.derived;

import entity.base.*;
import javafx.animation.AnimationTimer;

/**
 * The type Enemy.
 */
public class Enemy extends MoveableEntity implements Attackable, Despawnable, Renderable {
    private boolean despawn;

    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     */
    public Enemy(String name) {
        super(name);
        despawn = false;
        this.setFitWidth(30);
        this.setFitHeight(30);
        /**
         * Instantiates a new Enemy.
         *
         * @param name the name
         */
//        Random rand = new Random();
//        this.x = rand.nextInt((int) lowerBoundX, (int) upperBoundX);
//        this.y = upperBoundY;
//        this.velocityX = rand.nextInt(10) - 5;
        this.x = 300;
        this.y = upperBoundY;
        this.velocityY = 0;
        this.velocityX = 5;
        initializeTexture("Coin");
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        animationTimer.start();
    }


    @Override
    public boolean attack(Entity e) {
        return false;
    }

    @Override
    public boolean isDespawn() {
        return despawn;
    }

    @Override
    public void update() {
        if (this.x + velocityX > upperBoundX) {
            velocityX *= -1;
        } else if (this.x + velocityX < lowerBoundX) {
            velocityX *= -1;
        }
        this.x += velocityX;
        this.setX(x);
        this.setY(y);

    }
}
