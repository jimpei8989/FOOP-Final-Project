package utils;

public enum Action {
    DOWN("DOWN", new Coordinate(1, 0)), RIGHT("RIGHT", new Coordinate(0, 1)), UP("UP", new Coordinate(-1, 0)),
    LEFT("LEFT", new Coordinate(0, -1)), NO_OP("NO_OP", new Coordinate(0, 0)), ATTACK("ATTACK", new Coordinate(0, 0));

    private final String name;
    private final Coordinate coord;

    Action(String name, Coordinate coord) {
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
