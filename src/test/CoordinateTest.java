package test;

import org.junit.jupiter.api.Test;

import utils.Coordinate;

public class CoordinateTest {
    @Test
    void testAdd() {
        Coordinate coord = new Coordinate(3, 4), coord2 = new Coordinate(3.1, -0.3);
        System.out.printf("%s + (%d, %d) = %s\n", coord, 1, 2, coord.add(1, 2));
        System.out.printf("%s + (%f, %f) = %s\n", coord, coord2.getX(), coord2.getY(), coord.add(coord2));
    }
}
