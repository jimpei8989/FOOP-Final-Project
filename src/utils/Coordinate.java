package utils;

public class Coordinate<T> {
    private final T x, y;

    public Coordinate(T x, T y) {
        this.x = x;
        this.y = y;
    }

    T getX() {
        return this.x;
    }

    T getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.x.toString(), this.y.toString());
    }
}
