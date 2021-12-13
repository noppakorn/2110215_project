package entity.derived;


import controller.TextureLoader;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

/**
 * The type Coin box.
 */
public class CoinBox extends Box {
    /**
     * Instantiates a new Coin box.
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     */
    private int timer;
    public CoinBox(String name, int x, int y) {
        super(name, x, y);
        timer = 0;
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                aniamte();
                setImage();
            }

        };
        animationTimer.start();

    }
    public void setImage(){

        if (timer<28)this.setImage(TextureLoader.coinBox0);
        else if (timer<37)this.setImage(TextureLoader.coinBox1);
        else this.setImage(TextureLoader.coinBox2);
    }
    public void aniamte() {
        timer++;
        if (timer == 50) {
            timer = 0;
        }
    }
}
