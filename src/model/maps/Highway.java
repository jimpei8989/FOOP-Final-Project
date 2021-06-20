package model.maps;

import utils.Coordinate;
import utils.Direction;

public class Highway extends MapGrid {
    private final Coordinate destination;

    public Highway(Coordinate coord, Coordinate destination) {
        super(coord);
        this.destination = destination;
    }

    public boolean canPass(Coordinate source, Direction direction) {
        return true;
    }

    @Override
    public Coordinate transferTo(Direction direction) {
        return this.destination;
    }
}
