package model;

import java.util.ArrayList;
import java.util.List;

import utils.Coordinate;

public class Pacman {
    private int HP;
    private int score;
    private Coordinate coordinate;
    private List<State> states = new ArrayList<>();

    Pacman(Coordinate coordinate) {
        this.coordinate = coordinate;
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

    public boolean isActive() {
        // TODO
        return true;
    }

    public boolean canDecide() {
        // TODO
        return true;
    }

    public boolean canAttack() {
        // TODO
        return true;
    }

    public void addState(State state) {
        this.states.add(state);
    }

    private void removeState() {
        this.
    }

    void moveTo(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
