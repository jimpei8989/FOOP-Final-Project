package model.state;

import model.Pacman;
import model.interfaces.DecideCallback;
import model.interfaces.SwitchImageCallback;
import utils.Action;

public class Stunned extends State {
    private DecideCallback cannotDecide = new DecideCallback() {
        public Action onDecide(Action action) {
            return Action.NO_OP;
        }
    };

    private SwitchImageCallback dontChange = new SwitchImageCallback() {
        public int onSwitchImage(int idx) {
            return idx;
        }
    };

    public Stunned(Pacman target) {
        super("Stunned", target, 60);

    }

    public Stunned(State state, Pacman target) {
        super(state, target);
    }

    @Override
    public void onAdd() {
        this.target.addDecideCallback(this.cannotDecide);
        this.target.addSwitchImageCallback(this.dontChange);
    }

    @Override
    public State copy(Pacman target) {
        return new Stunned(this, target);
    }

    @Override
    public void onStateWillChange() {
        this.target.removeDecideCallback(this.cannotDecide);
        this.target.removeSwitchImageCallback(this.dontChange);
    }
}
