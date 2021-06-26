package model.map;

import utils.Coordinate;
import utils.Direction;

public class Wall extends MapGrid {
    public Wall(Coordinate coord) {
        super(coord);
    }

    public boolean canPass(Coordinate source, Direction direction) {
        return false;
    }
}
