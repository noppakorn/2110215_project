package entity.derived;

/**
 * The type Cactus.
 */
public class Pipe extends Box {
    /**
     * Instantiates a new Cactus.
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     */
    public Pipe(String name, double x, double y) {
        super(name, x, y);
        this.setFitHeight(140);
        this.setFitWidth(65);
        this.setX(x);
        this.setY(y);

        initializeTexture("pipeBig");
    }
}
