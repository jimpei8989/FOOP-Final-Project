package model.prop;

import utils.Coordinate;

public class BigPointProp extends PointProp {
    public BigPointProp(Coordinate coord) {
        super(coord);
        this.name = "BigPoint";
    }

    @Override
    protected int getPoint() {
        return 5;
    }
}
