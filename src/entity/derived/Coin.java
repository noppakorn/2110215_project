package entity.derived;

import controller.GameController;
import entity.base.Collectable;
import entity.base.Despawnable;
import entity.base.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;


/**
 * The type Coin.
 */
public class Coin extends Entity implements Collectable, Despawnable {
    private Image[] images = new Image[3];
    private int timer;
    /**
     * The Value.
     */
    protected int value;
    /**
     * The Despawn.
     */
    protected boolean despawn;

    /**
     * Instantiates a new Coin.
     */
    public Coin() {
        this(1, 0, 0);
    }

    /**
     * Instantiates a new Coin.
     *
     * @param x the x
     * @param y the y
     */
    public Coin(int x, int y) {
        this(1, x, y);
    }

    /**
     * Instantiates a new Coin.
     *
     * @param value the value
     * @param x     the x
     * @param y     the y
     */
    public Coin(int value, int x, int y) {
        super("Coin");
        this.value = value;
        this.setFitWidth(20);
        this.setFitHeight(20);
        this.setX(x);
        this.setY(y);
        this.timer = 0;
        images[0] = new Image(ClassLoader.getSystemResource(  "coin0.png").toString());
        images[1] = new Image(ClassLoader.getSystemResource(  "coin1.png").toString());
        images[2] = new Image(ClassLoader.getSystemResource(  "coin2.png").toString());
//        initializeTexture("Coin");
        despawn = false;
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                aniamte();
                setImage();
            }

        };
        animationTimer.start();
    }

    @Override
    public void collect() {
        GameController.increasePoint(value);
        GameController.increaseMoney(value);
        despawn = true;
    }

    public boolean isDespawn() {
        return despawn;
    }

    @Override
    public String toString() {
        return super.toString() + " valued " + value + " at (" + this.getX() + "," + this.getY() + ")";
    }
    public void setImage(){
        if (timer<28)this.setImage(images[0]);
        else if (timer<37)this.setImage(images[1]);
        else this.setImage(images[2]);
    }
    public void aniamte(){
       timer++;
       if (timer == 50){
           timer=0;
       }
    }

}
