package entity.derived;

import entity.base.Entity;
import entity.base.Solid;

public class CoinBox extends Coin implements Solid {
    private boolean haveOuterShell;

    public CoinBox(int x, int y) {
        this(1, x, y);
    }

    public CoinBox(int value, int x, int y) {
        super(value, x, y);
        this.setFitWidth(30);
        this.setFitHeight(30);
        despawn = false;
        haveOuterShell = true;
        initializeTexture("CoinBox");
    }

    @Override
    public boolean isDespawn() {
        return despawn;
    }

    @Override
    public void collect() {
        if (haveOuterShell) {
            haveOuterShell = false;
            initializeTexture("Coin");
        } else {
            super.collect();
        }
    }

    @Override
    public void blockPlayer(Entity e) {
        if (haveOuterShell) {
            if (e instanceof Player) {
                // TODO: Prevent player from phasing thourgh this box
            }
        }
    }
}
