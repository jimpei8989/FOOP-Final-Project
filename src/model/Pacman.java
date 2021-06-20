package model;

import model.interfaces.*;
import model.state.State;
import model.prop.Prop;
import model.weapon.Weapon;
import utils.Active;
import utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Pacman implements Locatable, Tickable, Active {
    private final String name;
    private int ID, HP, score, tickPerGrid;
    private Coordinate coordinate;
    private List<State> states = new ArrayList<>();
    private Weapon weapon;

    public Pacman(String name, int id, int hp, int score, int tickPerGrid, Coordinate coordinate) {
        this.name = name;
        this.ID = id;
        this.HP = hp;
        this.score = score;
        this.tickPerGrid = tickPerGrid;
        this.coordinate = coordinate;
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

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void onTurnBegin() {

    }

    public void onTurnEnd() {

    }

    public void onRoundBegin() {
    }

    public void onRoundEnd() {

    }

    public boolean isActive() {
        return true;
    }

    public boolean canDecide() {
        return true;
    }

    public boolean canAttack() {
        return true;
    }

    public void addState(State state) {
        this.states.add(state);
    }

    private void removeStates() {
        this.states.removeIf(s -> !s.isActive());
    }

    void moveTo(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    void onPropGet(Prop prop) {

    }

    void onWeaponGet(Weapon weapon) {
        this.weapon = weapon;
    }
}
