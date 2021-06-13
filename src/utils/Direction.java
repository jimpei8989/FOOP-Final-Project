package utils;

public enum Direction {
    DOWN("DOWN"), RIGHT("RIGHT"), UP("UP"), LEFT("LEFT");

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
