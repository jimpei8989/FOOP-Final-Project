package model.weapon;

import model.Pacman;
import model.interfaces.*;
import model.utils.CoolDown;
import utils.Active;
import utils.Coordinate;

public abstract class Weapon implements Active, Tickable, Locatable, Pickable, Droppable {
    private Coordinate coord;
    private boolean isPicked = false;
    private boolean isDropped = false;

    protected final CoolDown cd;
    protected Pacman owner;

    Weapon(Coordinate coord) {
        this.coord = coord;
        this.cd = new CoolDown(this.getDefaultCoolDown());
    }

    // Weapon
    public abstract int getDefaultCoolDown();

    public boolean canAttack() {
        return this.isPicked && !this.isDropped && this.cd.available();
    }

    public void use() {
        this.cd.reset();
    }

    // Checks if a coord (realCoordinate) can be attacked by the weapon
    public abstract boolean inRange(Coordinate coord);

    public abstract void onAttackSuccess(Pacman target);

    // Active
    public boolean isActive() {
        return !(isPicked && isDropped);
    }

    // Tickable
    @Override
    public void onRoundBegin() {
    }

    @Override
    public void onRoundEnd() {
    }

    @Override
    public void onTurnBegin() {
    }

    @Override
    public void onTurnEnd() {
        this.cd.update();
    }

    // Locatable
    public Coordinate getCoordinate() {
        assert !this.isPicked;
        return this.coord;
    }

    public void setCoordinate(Coordinate coord) {
        assert false;
    }

    public Coordinate getRealCoordinate() {
        return this.getCoordinate();
    }

    // Pickable
    @Override
    public void onPickUp(Pacman p) {
        this.isPicked = true;
        this.owner = p;
    }

    // Droppable
    @Override
    public void onDrop(Pacman p) {
        this.isDropped = true;
    }
}
