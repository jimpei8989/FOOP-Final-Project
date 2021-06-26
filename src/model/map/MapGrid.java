package model.map;

import utils.Coordinate;
import utils.Direction;

public abstract class MapGrid {
    private final Coordinate coord;

    public MapGrid(Coordinate coord) {
        this.coord = coord;
    }

    public abstract boolean canPass(Coordinate source, Direction direction);

    // Where will the pacman go when he steps on the grid
    public Coordinate transferTo(Direction direction) {
        return this.coord.add(direction.getCoord());
    }
}
