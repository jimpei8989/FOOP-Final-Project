package model.prop;

import model.Pacman;
import utils.Coordinate;

public abstract class PointProp extends Prop {
    protected abstract int getPoint();

    public PointProp(String name) {
        super(name);
    }

    public PointProp(String name, Coordinate coord) {
        super(name, coord);
    }

    public PointProp(Prop prop, Coordinate coord) {
        super(prop, coord);
    }

    @Override
    public void onPickUp(Pacman p) {
        super.onPickUp(p);
        p.setScore(p.getScore() + this.getPoint());
    }
}
