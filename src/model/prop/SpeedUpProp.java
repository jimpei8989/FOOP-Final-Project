package model.prop;

import model.Pacman;
import model.state.SpeedChange;
import utils.Coordinate;

public class SpeedUpProp extends SpeedProp {
    public SpeedUpProp() {
        super("SpeedUp");
    }

    public SpeedUpProp(Coordinate coord) {
        super("SpeedUp", coord);
    }

    public SpeedUpProp(Prop prop, Coordinate coord) {
        super(prop, coord);
    }

    @Override
    public Prop copy(Coordinate coord) {
        return new SpeedUpProp(this, coord);
    }

    @Override
    public void onPickUp(Pacman p) {
        super.onPickUp(p);
        p.addState(new SpeedChange(p, 6));
    }
}
