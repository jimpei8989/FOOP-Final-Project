package model;

import model.state.State;
import utils.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Pacman implements Locatable, Tickable {
    private int HP;
    private int score;
    private Coordinate coordinate;
    private List<State> states = new ArrayList<>();

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

    @Override
    public void onTurnBegin() {

    }

    @Override
    public void onTurnEnd() {

    }

    @Override
    public void onRoundBegin() {
    }

    @Override
    public void onRoundEnd() {

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

    private void removeStates() {
        this.states.removeIf(s -> !s.isActive());
    }

    void moveTo(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
