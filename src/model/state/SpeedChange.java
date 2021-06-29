package model.state;

import model.Pacman;
import model.utils.CoolDown;

public class SpeedChange extends State {
    protected CoolDown coolDown;
    protected int speedChange;
    protected int stepSize;

    public SpeedChange(Pacman target, int speedChange, int stepSize) {
        super("SpeedChange", target, 2 * 60);
        this.stepSize = stepSize;
        target.getMoveCd().setStepSize(this.stepSize);
    }

    public SpeedChange(Pacman target, int speedChange, int turn, int stepSize) {
        super("SpeedChange", target, turn);
        this.stepSize = stepSize;
        target.getMoveCd().setStepSize(this.stepSize);
    }

    public SpeedChange(State state, Pacman target) {
        super(state, target);
        target.getMoveCd().setStepSize(((SpeedChange) state).getStepSize());
    }

    @Override
    public State copy(Pacman target) {
        return new SpeedChange(this, target);
    }

    public int getSpeedChange() {
        return this.speedChange;
    }

    public int getStepSize() {
        return this.stepSize;
    }

    @Override
    public void onTurnBegin() {
        super.onTurnBegin();
    }

    @Override
    public void onStateWillChange() {
        super.onStateWillChange();
        this.target.getMoveCd().setStepSize(5);
    }

}
