package entity.derived;

import entity.base.MoveableEntity;
import javafx.animation.AnimationTimer;

/**
 * The type Box coin.
 */
public class BoxCoin extends MoveableEntity {
    private int timer;
    private AnimationTimer animationTimer;
    private int jumpTime;
    private int fallingTime;
    private int jumpHeight;

    /**
     * Instantiates a new Box coin.
     *
     * @param x the x
     * @param y the y
     */
    public BoxCoin(double x, double y) {
        super("coinBox", x, y);
        jumpTime = 15;
        fallingTime = 0;
        jumpHeight = 5;
        this.setX(x);
        this.setY(y);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                animate();
            }
        };
    }

    /**
     * Pop.
     */
    public void pop() {
        animationTimer.start();

    }

    /**
     * Animate.
     */
    public void animate() {
        if (timer < 3) initializeTexture("boxCoin0");
        else if (timer < 6) initializeTexture("boxCoin1");
        else if (timer < 9) initializeTexture("boxCoin2");
        else initializeTexture("boxCoin3");
        ++timer;
        if (timer == 12) {
            timer = 0;
        }
        if (jumpTime > 0) {
            y -= jumpHeight;
            --jumpTime;
            if (jumpTime == 0) fallingTime = 15;
        } else if (fallingTime > 0) {
            y += jumpHeight;
            --fallingTime;
        } else {
            animationTimer.stop();
        }
        this.setX(x);
        this.setY(y);
    }
}
