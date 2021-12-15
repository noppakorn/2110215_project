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
    /**
     * Should the Enemy be despawned
     */
    private boolean isDespawn;
    /**
     * JavaFx AnimationTimer to handle the CoinInCoinBox animation.
     */
    private AnimationTimer animationTimer;
    /**
     * The timer value for the animation.
     */
    private int timer;
    /**
     * The texture index of enemy to use
     */
    private int pictureInd;
    /**
     * The minimum coordinates the enemy can go to
     */
    private int lowerBound;
    /**
     * The maximum coordinates the enemy can go to
     */
    private int upperBound;


    /**
     * Instantiates a new Enemy.
     *
     * @param name       the name
     * @param x          the x
     * @param y          the y
     * @param lowerBound the lowerBound
     * @param upperBound the upperBound
     * @param direction  the direction, should only be 1 or -1
     */
    public Enemy(String name, double x, double y, int lowerBound, int upperBound, int direction) {
        super(name);
        isDespawn = false;
        setFitHeight(30);
        setFitWidth(30);
        this.x = x;
        this.y = upperBoundY + 20;
        this.velocityY = 0;
        this.velocityX = 3 * direction;
        this.pictureInd = 1;
        this.timer = 0;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
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
            isDespawn = true;
        } else {
            player.killPlayer();
            System.out.println(this + " killed " + player);
        }
    }

    @Override
    public boolean isDespawn() {
        return isDespawn;
    }

    @Override
    public void update() {
        ++timer;
        if (timer == 10) {
            timer = 0;
            if (pictureInd == 1) {
                pictureInd = 2;
                initializeTexture("enemy2");
            } else {
                pictureInd = 1;
                initializeTexture("enemy1");
            }
        }
        if (this.x + velocityX > upperBound) {
            velocityX *= -1;
        } else if (this.x + velocityX < lowerBound) {
            velocityX *= -1;
        }
        this.x += velocityX;
        this.setX(x);
        this.setY(y);

    }
}
