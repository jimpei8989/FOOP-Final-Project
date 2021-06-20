package model.weapon;

import model.Pacman;
import model.interfaces.*;
import utils.Active;
import utils.Coordinate;
import utils.Range;

public abstract class Weapon implements Active, Tickable, Locatable, Pickable, Droppable {
    private Range range;
    private Coordinate coord;
    private boolean isPicked = false;
    private boolean isDropped = false;

    Weapon(Coordinate coord, Range range) {
        this.coord = coord;
        this.range = range;
    }

    // Weapon
    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public abstract boolean canAttack();

    public abstract void use();

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
    }

    // Droppable
    @Override
    public void onDrop(Pacman p) {
        this.isDropped = true;
    }
}
