package utils;

public enum Direction {
    DOWN("DOWN", new Coordinate(1, 0), 90), RIGHT("RIGHT", new Coordinate(0, 1), 0),
    UP("UP", new Coordinate(-1, 0), 270), LEFT("LEFT", new Coordinate(0, -1), 180);

    private final String name;
    private final Coordinate coord;
    private final int degree;

    Direction(String name, Coordinate coord, int degree) {
        this.name = name;
        this.coord = coord;
        this.degree = degree;
    }

    public String toString() {
        return this.name;
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public int getDegree() {
        return this.degree;
    }
}
