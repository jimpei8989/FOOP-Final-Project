package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import model.interfaces.Locatable;
import model.prop.Prop;
import utils.Coordinate;
import utils.Direction;

public class Helper {
    private final World world;

    public Helper(World world) {
        this.world = world;
    }

    public Direction getNearByPropDirection(Pacman target) {
        Map<Coordinate, Locatable> coordsWithItems = this.world.getCoordsWithItems();
        ArrayList<Direction> directions = new ArrayList<>(
                Arrays.asList(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        for (Direction direction : directions) {
            Coordinate nextCoord = target.getCoordinate().add(direction.getCoord());
            if (coordsWithItems.containsKey(nextCoord) && coordsWithItems.get(nextCoord) instanceof Prop)
                return direction;
        }
        return null;
    }
}
