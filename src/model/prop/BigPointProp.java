package model.prop;

import utils.Coordinate;

public class BigPointProp extends PointProp {
    public BigPointProp(){
        super("BigPoint");
    }

    public BigPointProp(Coordinate coord) {
        super("BigPoint", coord);
    }

    public BigPointProp(Prop prop, Coordinate coord) {
        super(prop, coord);
    }

    @Override
    public Prop copy(Coordinate coord) {
        return new BigPointProp(this, coord);
    }

    @Override
    protected int getPoint() {
        return 5;
    }
}
