package model.interfaces;

import utils.Coordinate;

public interface Locatable {
    // Returns the coordinate on the map, both values should be integers.
    public Coordinate getCoordinate();

    // Sets the coordinate on the map, both values should be integers.
    public void setCoordinate(Coordinate coord);

    // Returns the *real coordinate on the map, both values should can be
    // floating-points.
    public Coordinate getRealCoordinate();
}
