package model.state;

import model.Pacman;
import model.interfaces.DecideCallback;
import utils.Action;

public class Stunned extends State {
    private DecideCallback cannotDecide = new DecideCallback() {
        public Action onDecide(Action action) {
            return Action.NO_OP;
        }
    };

    public Stunned(Pacman target) {
        super("Stunned", target, 150);
        this.target.addDecideCallback(cannotDecide);
    }

    public Stunned(State state, Pacman target) {
        super(state, target);
        this.target.addDecideCallback(cannotDecide);
    }

    @Override
    public State copy(Pacman target) {
        return new Stunned(this, target);
    }

    @Override
    public void onStateWillChange() {
        this.target.removeDecideCallback(this.cannotDecide);
    }
}
