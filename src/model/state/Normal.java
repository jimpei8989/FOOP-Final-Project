package model.state;

import model.Pacman;

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

    // Normal should not reduce turn
    @Override
    public void onTurnEnd() {}
}
