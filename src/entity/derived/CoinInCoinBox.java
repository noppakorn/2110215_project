package entity.derived;

import controller.GameController;
import entity.base.MoveableEntity;
import javafx.animation.AnimationTimer;

/**
 * The type Box coin.
 */
public class CoinInCoinBox extends MoveableEntity {
    /**
     * The timer value for the animation.
     */
    private int timer;
    /**
     * JavaFx AnimationTimer to handle the CoinInCoinBox animation.
     */
    private AnimationTimer animationTimer;
    /**
     * The time take for the coin to jumps up from the box.
     */
    private int jumpTime;
    /**
     * The time take for the coin to fall back from the air.
     */
    private int fallingTime;
    /**
     * The height the coin will jumps up when it gets pop.
     */
    private int jumpHeight;

    /**
     * Instantiates a new Coin that is inside coin box.
     *
     * @param x the x coordinates
     * @param y the y coordinates
     */
    public CoinInCoinBox(double x, double y) {
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
     * Handle animation when the coin pops out from the box.
     */
    public void pop() {
        GameController.increasePoint(1);
        GameController.increaseMoney(1);
        animationTimer.start();
    }

    /**
     * Handle animation for the coin.
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
