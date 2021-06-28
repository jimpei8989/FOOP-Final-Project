package model.prop;

import model.Pacman;
import model.state.SpeedChange;
import utils.Coordinate;

public class SlowDownProp extends SpeedProp {
    public SlowDownProp() {
        super("SlowDown");
    }

    public SlowDownProp(Coordinate coord) {
        super("SlowDown", coord);
    }

    public SlowDownProp(Prop prop, Coordinate coord) {
        super(prop, coord);
    }

    @Override
    public Prop copy(Coordinate coord) {
        return new SlowDownProp(this, coord);
    }

    @Override
    public void onPickUp(Pacman p) {
        super.onPickUp(p);
        p.addState(new SpeedChange(p, 4));
    }
}
