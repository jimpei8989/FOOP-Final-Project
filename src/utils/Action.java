package utils;

public enum Action {
    DOWN("DOWN", new Coordinate<Integer>(1, 0)), RIGHT("RIGHT", new Coordinate<Integer>(0, 1)),
    UP("UP", new Coordinate<Integer>(-1, 0)), LEFT("LEFT", new Coordinate<Integer>(0, -1)),
    NO_OP("NO_OP", new Coordinate<Integer>(0, 0)), ATTACK("ATTACK", new Coordinate<Integer>(0, 0));

    private final String name;
    private final Coordinate<Integer> coord;

    Action(String name, Coordinate<Integer> coord) {
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
