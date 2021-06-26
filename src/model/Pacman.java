package model;

import model.interfaces.*;
import model.state.State;
import model.prop.Prop;
import model.weapon.Weapon;
import utils.Active;
import utils.Coordinate;
import utils.Direction;

import java.util.ArrayList;
import java.util.List;

public class Pacman implements Locatable, Tickable, Active {
    private final String name;
    private int ID, HP, score, tickPerGrid;
    private Coordinate coordinate;
    private List<State> states = new ArrayList<>();
    private Weapon weapon;
    private Direction facing;

    public Pacman(String name, int id, int hp, int score, int tickPerGrid, Coordinate coordinate) {
        this.name = name;
        this.ID = id;
        this.HP = hp;
        this.score = score;
        this.tickPerGrid = tickPerGrid;
        this.coordinate = coordinate;
        this.facing = Direction.RIGHT;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public void setHP(int hp) {
        this.HP = hp;
    }

    public int getHP() {
        return this.HP;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setTickPerGrid(int tickPerGrid) {
        this.tickPerGrid = tickPerGrid;
    }

    public int getTickPerGrid() {
        return this.tickPerGrid;
    }

    public Direction getFacing() {
        return this.facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public boolean canDecide() {
        return true;
    }

    public boolean canAttack() {
        return true;
    }

    // State related
    public void addState(State state) {
        this.states.add(state);
    }

    private void removeStates() {
        this.states.removeIf(s -> !s.isActive());
    }

    public void onPropGet(Prop prop) {
    }

    public void onWeaponGet(Weapon weapon) {
        this.weapon = weapon;
    }

    // Locatable
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getRealCoordinate() {
        // TODO: the case when the pacman is moving
        return this.getCoordinate();
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

    // Active
    public boolean isActive() {
        return true;
    }
}
