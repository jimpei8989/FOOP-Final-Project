package test.weapon;

import model.Pacman;
import utils.Coordinate;

public class TestUtils {
    public class FakePacman extends Pacman {
        public FakePacman(Coordinate coord) {
            super("Fake Pacman", 0, 1000, 0, 10, coord);
        }
    }

    public Pacman fakePacman(Coordinate coord) {
        return new FakePacman(coord);
    }
}
