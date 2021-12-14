package entity.derived;


import javafx.animation.AnimationTimer;

/**
 * A box that hold a coin.
 */
public class CoinBox extends Box {
    private boolean isEmpty;
    private int timer;
    private AnimationTimer animationTimer;
    private CoinInCoinBox coinInCoinBox;

    /**
     * Instantiates a new Coin Box.
     *
     * @param name the name of the box
     * @param x    the x coordinates
     * @param y    the y coordinates
     */
    public CoinBox(String name, double x, double y) {
        super(name, x, y);
        timer = 0;
        isEmpty = false;
        coinInCoinBox = new CoinInCoinBox(x + 17, y);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animate();
            }

        };
        animationTimer.start();

    }

    /**
     * Sets image.
     */
    public void setImage() {
        if (isEmpty) {
            initializeTexture("coinBox3");
            animationTimer.stop();
        } else {
            if (timer < 28) initializeTexture("coinBox0");
            else if (timer < 37) initializeTexture("coinBox1");
            else initializeTexture("coinBox2");
        }
    }

    /**
     * Animate.
     */
    public void animate() {
        timer++;
        if (timer == 50) {
            timer = 0;
        }
        setImage();
    }

    /**
     * Gets is empty.
     *
     * @return the is empty
     */
    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    /**
     * Sets is empty.
     *
     * @param isEmpty the is empty
     */
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * Coin pop.
     */
    public void coinPop() {
        coinInCoinBox.pop();
    }

    /**
     * Gets coin in coin box.
     *
     * @return the coin in coin box
     */
    public CoinInCoinBox getCoinInCoinBox() {
        return coinInCoinBox;
    }

}
