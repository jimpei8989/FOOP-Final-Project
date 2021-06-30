package controller;

import java.util.Random;

import model.Pacman;
import model.World;
import utils.Action;
import utils.Direction;

public class SimpleController extends Controller {
    private final Random random = new Random();

    public SimpleController() {
    }

    public SimpleController(Pacman target, World world) {
        super(target, world);
    }

    @Override
    public Controller copy(Pacman target, World world) {
        return new SimpleController(target, world);
    }

    @Override
    public Action decide() {
        Direction direction = this.helper.getNearByPropDirection(this.target);
        if (direction == null) {
            int choice = random.nextInt(4);
            switch (choice) {
                case 0:
                    return Action.DOWN;
                case 1:
                    return Action.RIGHT;
                case 2:
                    return Action.UP;
                default:
                    return Action.LEFT;
            }
        }
        switch (direction) {
            case DOWN:
                return Action.DOWN;
            case RIGHT:
                return Action.RIGHT;
            case UP:
                return Action.UP;
            default:
                return Action.LEFT;
        }
    }
}
