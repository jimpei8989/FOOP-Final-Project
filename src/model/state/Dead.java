package model.state;

import model.Pacman;

public class Dead extends State {

    public Dead(Pacman target) {
        super("Dead", target, 300);
    }

    public Dead(State state, Pacman target) {
        super(state, target);
    }

    @Override
    public State copy(Pacman target) {
        return new Dead(this, target);
    }

    @Override
    public boolean needToRender() {
        return false;
    }

    @Override
    public void onStateWillChange() {
        this.target.setFullHP();
        this.target.addState(new Revive(this.target));
    }
}
