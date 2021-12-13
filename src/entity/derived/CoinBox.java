package entity.derived;


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
    public CoinBox(String name, int x, int y) {
        super(name, x, y);
        initializeTexture("CoinBox");
    }

}
