package model.state;

import model.Pacman;
import model.interfaces.AttackCallback;
import model.interfaces.TakeDamageCallback;

public class Berserker extends State implements AttackCallback, TakeDamageCallback {
    public Berserker(Pacman target) {
        super("Berserker", target, 5 * 60);
    }

    public Berserker(State state, Pacman target) {
        super(state, target);
    }

    @Override
    public State copy(Pacman target) {
        return new Berserker(this, target);
    }

    @Override
    public int onAttackBegin(int damage) {
        return damage * 2;
    }

    @Override
    public int onTakeDamage(int damage) {
        return damage / 2;
    }

    @Override
    public void onAdd() {
        this.target.addAttackCallback(this);
        this.target.addTakeDamageCallback(this);
    }

    @Override
    public void onStateWillChange() {
        this.target.removeAttackCallback(this);
        this.target.removeTakeDamageCallback(this);
    }

}
