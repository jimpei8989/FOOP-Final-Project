package utils;

public enum Action {
    DOWN("DOWN", new Coordinate(1, 0), Direction.DOWN), RIGHT("RIGHT", new Coordinate(0, 1), Direction.RIGHT),
    UP("UP", new Coordinate(-1, 0), Direction.UP), LEFT("LEFT", new Coordinate(0, -1), Direction.LEFT),
    NO_OP("NO_OP", new Coordinate(0, 0), null), ATTACK("ATTACK", new Coordinate(0, 0), null);

    private final String name;
    private final Coordinate coord;
    private final Direction direction;

    Action(String name, Coordinate coord, Direction direction) {
        this.name = name;
        this.coord = coord;
        this.direction = direction;
    }

    public String toString() {
        return this.name;
    }

    public Coordinate getCoord() {
        return this.coord;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
