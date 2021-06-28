package model.state;

import model.Pacman;

public class Berserker extends State {
    public Berserker(Pacman target) {
        super("Berserker", target, 90);
    }

    public Berserker(State state, Pacman target) {
        super(state, target);
    }

    @Override
    public State copy(Pacman target) {
        return new Berserker(this, target);
    }

}
