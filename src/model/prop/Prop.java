package model.prop;

import model.interfaces.*;
import model.Pacman;
import utils.Active;
import utils.Coordinate;

public abstract class Prop implements Active, Tickable, Locatable, Pickable {
    private final Coordinate coord;
    private boolean isPicked = false;
    private String name;

    public Prop(String name) {
        this.coord = new Coordinate(0, 0);
        this.name = name;
    }

    public Prop(String name, Coordinate coord) {
        this.coord = coord;
        this.name = name;
    }

    public Prop(Prop prop, Coordinate coord) {
        this.coord = coord;
        this.name = prop.name;
        this.isPicked = prop.isPicked;
    }

    public abstract Prop copy(Coordinate coord);

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
        return !this.isPicked;
    }
}
