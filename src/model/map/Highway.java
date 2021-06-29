package model.map;

import model.utils.CoolDown;
import utils.Coordinate;
import utils.Direction;

public class Highway extends MapGrid {
    private final Coordinate destination;
    private final CoolDown cd;
    private Highway pairing;

    public Highway(Coordinate coord, Coordinate destination) {
        super(coord);
        this.destination = destination;
        this.cd = new CoolDown(1, 120);
    }

    public void setPairing(Highway pairing) {
        this.pairing = pairing;
    }

    public boolean canPass(Coordinate source, Direction direction) {
        return true;
    }

    @Override
    public Coordinate transferTo(Direction direction) {
        if (this.cd.available()) {
            return this.destination;
        } else {
            return super.transferTo(direction);
        }
    }

    public void resetCd() {
        this.cd.reset();
    }

    @Override
    public void onMoveEnd() {
        if (this.cd.available()) {
            this.resetCd();
            this.pairing.resetCd();
        }
    }

    @Override
    public void onRoundEnd() {
        this.cd.update();
    }
}
