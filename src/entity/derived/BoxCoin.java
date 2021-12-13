package entity.derived;

import controller.TextureLoader;
import entity.base.MoveableEntity;
import javafx.animation.AnimationTimer;

public class BoxCoin extends MoveableEntity {
    private int timer;
    private AnimationTimer animationTimer;
    private int jumpTime;
    private int fallingTime;
    private int jumpHeight;

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
        animationTimer.start();
    }

    public void coinPop() {
        animationTimer.start();

    }

    public void animate() {
        if (timer<3) setImage(TextureLoader.coinBox0);
        else if (timer<6) setImage(TextureLoader.coinBox1);
        else if (timer<9) setImage(TextureLoader.coinBox2);
        else setImage(TextureLoader.coinBox3);
        ++timer;
        if (timer == 12) {
            timer = 0;
        }
        if (jumpTime > 0) {
            y -= jumpHeight;
            --jumpTime;
            if (jumpTime == 0) fallingTime = 15;
        } else if (fallingTime > 0){
            y += jumpHeight;
            --fallingTime;
        }
        else {
            animationTimer.stop();
        }
        this.setX(x);
        this.setY(y);
    }
}
