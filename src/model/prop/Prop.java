package model.prop;

import model.interfaces.*;
import model.Pacman;
import utils.Active;
import utils.Coordinate;

public abstract class Prop implements Active, Tickable, Locatable, Pickable {
    private final Coordinate coord;
    private boolean isPicked = false;
    protected String name;

    public Prop(Coordinate coord) {
        this.coord = coord;
        this.name = "";
    }

    public String getName() {
        return this.name;
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

    // Active
    @Override
    public boolean isActive() {
        return this.isPicked;
    }
}
