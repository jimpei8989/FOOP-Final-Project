package model.prop;

import utils.Coordinate;

public class SmallPointProp extends PointProp {
    public SmallPointProp(Coordinate coord) {
        super(coord);
        this.name = "SmallPoint";
    }

    @Override
    protected int getPoint() {
        return 1;
    }
}
