package model.map;

import utils.Coordinate;
import utils.Direction;

public class Road extends MapGrid {
    public Road(Coordinate coord) {
        super(coord);
    }

    public boolean canPass(Coordinate source, Direction direction) {
        return true;
    }
}
