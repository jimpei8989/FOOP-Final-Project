package model.prop;

import model.Pacman;
import model.state.Drunk;
import utils.Coordinate;

public class WineProp extends PointProp {
    public WineProp() {
        super("Wine");
    }

    public WineProp(Coordinate coord) {
        super("Wine", coord);
    }

    public WineProp(Prop prop, Coordinate coord) {
        super(prop, coord);
    }

    @Override
    public Prop copy(Coordinate coord) {
        return new WineProp(this, coord);
    }

    @Override
    protected int getPoint() {
        return 10;
    }

    @Override
    public void onPickUp(Pacman p) {
        super.onPickUp(p);
        p.addState(new Drunk(p));
    }
}
