package block;

import block.Block;
import entity.base.Movable;

public class MovableBlock extends Block implements Movable {
    @Override
    public boolean move() {
        return false;
    }
}
