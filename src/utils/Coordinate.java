package utils;

public class Coordinate {
    private final Number x, y;

    public Coordinate(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    public Number getX() {
        return this.x;
    }

    public Number getY() {
        return this.y;
    }

    public Coordinate add(Coordinate coord) {
        if (this.x instanceof Integer && coord.x instanceof Integer && this.y instanceof Integer
                && coord.y instanceof Integer)
            return new Coordinate(this.x.intValue() + coord.x.intValue(),
                    this.y.intValue() + coord.y.intValue());
        return new Coordinate(this.x.doubleValue() + coord.x.doubleValue(),
                this.y.doubleValue() + coord.y.doubleValue());
    }

    public Coordinate add(Number x, Number y) {
        if (this.x instanceof Integer && x instanceof Integer && this.y instanceof Integer
                && y instanceof Integer)
            return new Coordinate(this.x.intValue() + x.intValue(),
                    this.y.intValue() + y.intValue());
        return new Coordinate(this.x.doubleValue() + x.doubleValue(),
                this.y.doubleValue() + y.doubleValue());
    }

    @Override
    public int hashCode() {
        Integer xHash = x.hashCode();
        return xHash.hashCode() ^ y.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Coordinate coord = (Coordinate) obj;
        return x.equals(coord.x) && y.equals(coord.y);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.x.toString(), this.y.toString());
    }
}
