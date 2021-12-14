package entity.derived;

import controller.GameController;
import entity.base.Despawnable;
import entity.base.MoveableEntity;
import entity.base.Renderable;
import javafx.animation.AnimationTimer;

/**
 * The type Enemy.
 */
public class Enemy extends MoveableEntity implements Despawnable, Renderable {
    private boolean despawn;
    private AnimationTimer animationTimer;
    private int timer;
    private int pic;


    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     */
    public Enemy(String name, double x, double y, double direction) {
        super(name);
        despawn = false;
        setFitHeight(30);
        setFitWidth(30);
        this.x = x;
        this.y = upperBoundY + 20;
        this.velocityY = 0;
        if (direction >= 0) {
            this.velocityX = 2;
        } else {
            this.velocityX = -2;
            System.out.println("negative");
        }
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


    /**
     * Attack the player.
     *
     * @param player the player to be attacked
     */
    public void attack(Player player) {
        if (player.isFalling()) {
            System.out.println(this + " is killed");
            GameController.increasePoint(1);
            despawn = true;
        } else {
            player.killPlayer();
            System.out.println(this + " killed " + player);
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
