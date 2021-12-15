package entity.derived;


import javafx.animation.AnimationTimer;

/**
 * A box that hold a coin.
 */
public class CoinBox extends Box {
    /**
     * The state that if the CoinBox is empty
     */
    private boolean isEmpty;
    /**
     * The timer value for the animation.
     */
    private int timer;
    /**
     * JavaFx AnimationTimer to handle the CoinBox animation.
     */
    private AnimationTimer animationTimer;
    /**
     * The coin object that is stored in the box.
     */
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
     * Sets image of the CoinBox.
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
     * Handle animation for the CoinBox
     */
    public void animate() {
        timer++;
        if (timer == 50) {
            timer = 0;
        }
        setImage();
    }

    /**
     * Get the status if the CoinBox have coin in it.
     *
     * @return the status if the CoinBox in empty
     */
    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    /**
     * Set the empty status of the CoinBox.
     *
     * @param isEmpty the isEmpty status to be set
     */
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    /**
     * Handle the coin pop out of the box when the player hits the box.
     */
    public void coinPop() {
        coinInCoinBox.pop();
    }

    /**
     * Gets CoinInCoinBox object.
     *
     * @return the CoinInCoinBox object
     */
    public CoinInCoinBox getCoinInCoinBox() {
        return coinInCoinBox;
    }

}
