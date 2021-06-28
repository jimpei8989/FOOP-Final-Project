package model.prop;

import java.util.Random;

import utils.Coordinate;

public class PropUtils {
    private static boolean inRange(int x, int low, int high) {
        return low <= x && x < high;
    }

    public static Prop getRandomProp(Random random, Coordinate coord) {
        int choice = random.nextInt(100);
        if (inRange(choice, 0, 50))
            return new SmallPointProp(coord);
        else if (inRange(choice, 50, 70))
            return new BigPointProp(coord);
        else if (inRange(choice, 70, 85))
            return new SpeedUpProp(coord);
        else
            return new SlowDownProp(coord);
    }
}
