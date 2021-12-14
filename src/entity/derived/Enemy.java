package entity.derived;

import controller.GameController;
import entity.base.Attackable;
import entity.base.Despawnable;
import entity.base.Entity;
import entity.base.MoveableEntity;
import entity.base.Renderable;
import javafx.animation.AnimationTimer;

/**
 * The type Enemy.
 */
public class Enemy extends MoveableEntity implements Attackable, Despawnable, Renderable {
    private boolean despawn;
    private AnimationTimer animationTimer;
    private int timer;
    private int pic;


    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     */
    public Enemy(String name) {
        super(name);
        despawn = false;
        setFitHeight(30);
        setFitWidth(30);
        this.x = 300;
        this.y = upperBoundY + 20;
        this.velocityY = 0;
        this.velocityX = 3;
        this.pic = 1;
        this.timer = 0;
        initializeTexture("enemy1");
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        animationTimer.start();
    }


    @Override
    public void attack(Entity e) {
        if (e instanceof Player) {
            if (((Player) e).isFalling()) {
                System.out.println(this + " is killed");
                GameController.increasePoint(1);
                despawn = true;
            } else {
                ((Player) e).killPlayer();
                System.out.println(this + " killed " + e);
            }
        }
    }

    @Override
    public boolean isDespawn() {
        return despawn;
    }

    @Override
    public void update() {
        ++timer;
        if (timer == 10) {
            timer = 0;
            if (pic == 1) {
                pic = 2;
                initializeTexture("enemy2");
            } else {
                pic = 1;
                initializeTexture("enemy1");
            }
        }
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
