package model.state;

import model.Pacman;
import model.interfaces.Tickable;
import utils.Active;

public abstract class State implements Tickable, Active {
    private String name;
    protected Pacman target;
    private int turn = 1;
    private int fullTurn;

    public State(String name, Pacman target) {
        this.name = name;
        this.target = target;
    }

    public State(String name, Pacman target, int turn) {
        this.name = name;
        this.target = target;
        this.turn = turn;
        this.fullTurn = turn;
    }

    // copy constructor
    public State(State state, Pacman target) {
        this.name = state.name;
        this.target = target;
        this.turn = state.turn;
        this.fullTurn = state.turn;
    }

    public abstract State copy(Pacman target);

    public String getName() {
        return this.name;
    }

    public void restoreFullTurn() {
        this.turn = this.fullTurn;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void onTurnBegin() {
    }

    @Override
    public void onTurnEnd() {
        this.turn--;
    }

    @Override
    public void onRoundBegin() {
    }

    @Override
    public void onRoundEnd() {
    }

    public void onAdd() {

    }

    public void onStateWillChange() {
    }

    @Override
    public boolean isActive() {
        return this.turn > 0;
    }

    public boolean needToRender() {
        return true;
    }
}
