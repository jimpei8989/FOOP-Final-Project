package model.weapon;

import model.Pacman;
import model.state.State;
import utils.Coordinate;

public abstract class StateChangingWeapon extends Weapon {
    private State state;

    public StateChangingWeapon() {
        super();
    }

    StateChangingWeapon(Coordinate coord, State state) {
        super(coord);
        this.state = state;
    }

    public StateChangingWeapon(Weapon weapon, Coordinate coord, State state) {
        super(weapon, coord);
        this.state = state;
    }

    @Override
    public void onAttackSuccess(Pacman target) {
        target.addState(this.state.copy(target));
    }
}
