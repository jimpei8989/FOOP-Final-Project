package utils;

public enum Direction {
    DOWN("DOWN", new Coordinate<Integer>(1, 0)), RIGHT("RIGHT", new Coordinate<Integer>(0, 1)), UP(
            "UP", new Coordinate<Integer>(-1, 0)), LEFT("LEFT", new Coordinate<Integer>(0, -1));

    private final String name;
    private Coordinate<Integer> coord;

    Direction(String name, Coordinate<Integer> coord) {
        this.name = name;
        this.coord = coord;
    }

    public String toString() {
        return this.name;
    }

    public Coordinate<Integer> getCoord() {
        return this.coord;
    }
}
