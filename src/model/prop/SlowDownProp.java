package model.prop;

import model.Pacman;
import model.state.SpeedChange;
import utils.Coordinate;

public class SlowDownProp extends SpeedProp {
    public SlowDownProp(Coordinate coord) {
        super(coord);
        this.name = "SlowDown";

    }

    @Override
    public void onPickUp(Pacman p) {
        super.onPickUp(p);
        p.addState(new SpeedChange(p, 4));
    }
}
