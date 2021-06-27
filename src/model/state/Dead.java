package model.state;

import model.Pacman;

public class Dead extends State {

    public Dead(Pacman target) {
        super("Dead", target, 100);
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
