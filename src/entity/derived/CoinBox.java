package entity.derived;

import entity.base.Entity;
import entity.base.Solid;

public class CoinBox extends Coin implements Solid {
    private boolean haveOuterShell;

    public CoinBox() {
        super();
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
