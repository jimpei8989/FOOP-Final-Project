package model.state;

import java.util.Random;

import model.Pacman;
import model.interfaces.DecideCallback;
import model.interfaces.SwitchImageCallback;
import utils.Action;

public class Drunk extends State {
    private final Random random = new Random();

    private DecideCallback upsiteDown = new DecideCallback() {
        public Action onDecide(Action action) {
            switch (action) {
                case UP:
                    return Action.DOWN;
                case DOWN:
                    return Action.UP;
                case RIGHT:
                    return Action.LEFT;
                case LEFT:
                    return Action.RIGHT;
                case ATTACK:
                    if (random.nextBoolean())
                        return Action.ATTACK;
                    else
                        return Action.NO_OP;
                default:
                    return Action.NO_OP;
            }
        }
    };

    private SwitchImageCallback randomChange = new SwitchImageCallback() {
        public int onSwitchImage(int idx) {
            return random.nextInt() & Integer.MAX_VALUE;
        }
    };

    public Drunk(Pacman target) {
        super("Drunk", target, 150);
    }

    public Drunk(State state, Pacman target) {
        super(state, target);
    }

    @Override
    public void onAdd() {
        this.target.addDecideCallback(this.upsiteDown);
        this.target.addSwitchImageCallback(this.randomChange);
    }

    @Override
    public State copy(Pacman target) {
        return new Drunk(this, target);
    }

    @Override
    public void onStateWillChange() {
        this.target.removeDecideCallback(this.upsiteDown);
        this.target.removeSwitchImageCallback(this.randomChange);
    }
}
