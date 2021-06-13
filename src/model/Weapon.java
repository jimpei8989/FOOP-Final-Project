package model;

import utils.Range;

public abstract class Weapon implements Locatable, Pickable, Droppable, Tickable {
    private Range range;

    Weapon(Range range) {
        this.range = range;
    }

    // Locatable

    // Pickable
    public void onPickUp(Pacman p) {
    }

    // Droppable
    public void onDrop(Pacman p) {
    }

    // Tickable
    public void onTurnBegin() {
    }

    public void onTurnEnd() {
    }

    public void onRoundBegin() {
    }

    public void onRoundEnd() {
    }

    // Weapon

    public Range getRange() {
        return this.range;
    }

    public void setRange(Range range) {
        this.range = range;
        return;
    }

    public boolean canAttack() {
        return true;
    }

    public void use() {

    }
}