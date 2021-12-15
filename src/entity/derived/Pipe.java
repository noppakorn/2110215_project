package entity.derived;

/**
 * The type Cactus.
 */
public class Pipe extends Box {
    /**
     * Instantiates a new Pipe.
     *
     * @param name the name
     * @param x    the x coordinates
     * @param y    the y coordinates
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
