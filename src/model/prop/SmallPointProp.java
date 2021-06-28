package model.prop;

import utils.Coordinate;

public class SmallPointProp extends PointProp {
    public SmallPointProp() {
        super("SmallPoint");
    }

    public SmallPointProp(Coordinate coord) {
        super("SmallPoint", coord);
    }

    public SmallPointProp(Prop prop, Coordinate coord) {
        super(prop, coord);
    }

    @Override
    public Prop copy(Coordinate coord) {
        return new SmallPointProp(this, coord);
    }

    @Override
    protected int getPoint() {
        return 1;
    }
}
