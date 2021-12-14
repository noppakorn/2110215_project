package entity.derived;


import controller.TextureLoader;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import scene.LevelGenerator;

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
    private boolean isEmpty;
    private int timer;
    private AnimationTimer animationTimer;
    private BoxCoin boxCoin;

    public CoinBox(String name, int x, int y) {

        super(name, x, y);
        timer = 0;
        isEmpty = false;
        boxCoin = new BoxCoin(x + 17,y);
        LevelGenerator.entities.add(boxCoin);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                aniamte();
            }

        };
        animationTimer.start();

    }

    public void setImage() {
        if (isEmpty) {
            initializeTexture("coinBox3");
            animationTimer.stop();
        }
        else {
            if (timer < 28) initializeTexture("coinBox0");
            else if (timer < 37) initializeTexture("coinBox1");
            else initializeTexture("coinBox2");
        }
    }

    public void aniamte() {
        timer++;
        if (timer == 50) {
            timer = 0;
        }
        setImage();
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean getIsEmpty() {
        return this.isEmpty;
    }
    public void coinPop(){
        boxCoin.pop();
        LevelGenerator.entities.remove(boxCoin);
    }
}
