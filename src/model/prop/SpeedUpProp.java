package model.prop;

import model.Pacman;
import model.state.SpeedChange;
import utils.Coordinate;

public class SpeedUpProp extends SpeedProp {
    public SpeedUpProp(Coordinate coord) {
        super(coord);
        this.name = "SpeedUp";

    }

    @Override
    public void onPickUp(Pacman p) {
        super.onPickUp(p);
        p.addState(new SpeedChange(p, -4));
    }
}
