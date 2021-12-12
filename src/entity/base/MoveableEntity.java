package entity.base;

public class MoveableEntity extends Entity{
    protected double velocityY;
    protected double velocityX;
    protected double sceneUpperBoundX = 700;
    protected double upperBoundX = 700;
    protected double upperBoundY = 400;
    protected double lowerBoundX = 20;
    public MoveableEntity(String name){
        super(name);
    }
}
