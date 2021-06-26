package model.prop;

import utils.Coordinate;

public class SmallPointProp extends PointProp {
    public SmallPointProp(Coordinate coord) {
        super(coord);
    }

    @Override
    protected int getPoint() {
        return 1;
    }
}
