package model.state;

import model.Pacman;
import model.utils.CoolDown;

public class Normal extends State {
    public Normal(Pacman target) {
        super("Normal", target);
    }

    public Normal(State state, Pacman target) {
        super(state, target);
    }

    @Override
    public State copy(Pacman target) {
        return new Normal(this, target);
    }

    public int getStepSize() {
        return target.getDefaultMoveCoolDown();
    }

    // Normal should not reduce turn
    @Override
    public void onTurnEnd() {
    }
}
