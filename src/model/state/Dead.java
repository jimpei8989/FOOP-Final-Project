package model.state;

import model.Pacman;
import model.interfaces.TakeDamageCallback;

public class Dead extends State implements TakeDamageCallback {

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
        this.target.removeTakeDamageCallback(this);
        this.target.setFullHP();
        this.target.addState(new Revive(this.target));
    }

    // Cannot take damage if dead
    @Override
    public int onTakeDamage(int damage) {
        return 0;
    }

    @Override
    public void onAdd() {
        this.target.addTakeDamageCallback(this);
    }

}
