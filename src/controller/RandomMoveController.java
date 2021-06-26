package controller;

import java.util.Random;

import utils.Action;

public class RandomMoveController extends Controller {
    private final Random random;

    public RandomMoveController() {
        random = new Random();
    }

    public Action decide() {
        int choice = random.nextInt(4);
        if (choice == 0) {
            return Action.DOWN;
        } else if (choice == 1) {
            return Action.RIGHT;
        } else if (choice == 2) {
            return Action.UP;
        } else {
            return Action.LEFT;
        }
    }
}
