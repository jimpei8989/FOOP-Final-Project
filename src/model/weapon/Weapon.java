package model.weapon;

import model.interfaces.*;
import utils.Range;

public abstract class Weapon implements Locatable, Pickable, Droppable, Tickable {
    private Range range;

    Weapon(Range range) {
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
}