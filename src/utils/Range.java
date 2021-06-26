package utils;

import java.util.*;

public class Range {
    List<Coordinate> range;

    Range(List<Coordinate> range) {
        this.range = range;
    }

    public Range fromCoordinate(Coordinate coord) {
        List<Coordinate> newRange = new ArrayList<>();
        for (Coordinate rangeCoord : range) {
            newRange.add(coord.add(rangeCoord));
        }
        return new Range(newRange);
    }
}
