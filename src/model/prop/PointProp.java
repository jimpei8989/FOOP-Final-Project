package model.prop;

import model.Pacman;
import utils.Coordinate;

public abstract class PointProp extends Prop {
    protected abstract int getPoint();

    PointProp(Coordinate coord) {
        super(coord);
    }

    @Override
    public void onPickUp(Pacman p) {
        super.onPickUp(p);
        p.setScore(p.getScore() + this.getPoint());
    }
}
