package utils;

public enum Direction {
    DOWN("DOWN", new Coordinate(1, 0)), RIGHT("RIGHT", new Coordinate(0, 1)), UP("UP", new Coordinate(-1, 0)),
    LEFT("LEFT", new Coordinate(0, -1));

    private final String name;
    private Coordinate coord;

    Direction(String name, Coordinate coord) {
        this.name = name;
        this.coord = coord;
    }

    public String toString() {
        return this.name;
    }

    public Coordinate getCoord() {
        return this.coord;
    }
}
