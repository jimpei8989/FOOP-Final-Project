package utils;

public enum Action {
    DOWN("DOWN"), RIGHT("RIGHT"), UP("UP"), LEFT("LEFT"), NO_OP("NO_OP"), ATTACK("ATTACK");

    private final String name;

    Action(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
