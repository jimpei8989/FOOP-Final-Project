package model.state;

import model.Pacman;

public class SpeedChange extends State {
    protected int stepSize;

    public SpeedChange(Pacman target, int stepSize) {
        super("SpeedChange", target, 2 * 60);
        this.stepSize = stepSize;
        target.getMoveCd().setStepSize(this.stepSize);
    }

    public SpeedChange(Pacman target, int turn, int stepSize) {
        super("SpeedChange", target, turn);
        this.stepSize = stepSize;
        target.getMoveCd().setStepSize(this.stepSize);
    }

    public SpeedChange(State state, Pacman target) {
        super(state, target);
        target.getMoveCd().setStepSize(((SpeedChange) state).getStepSize());
    }

    @Override
    public String getName() {
        return this.stepSize > this.target.getDefaultStepSize() ? "SpeedUp" : "SlowDown";
    }

    @Override
    public State copy(Pacman target) {
        return new SpeedChange(this, target);
    }

    public int getStepSize() {
        return this.stepSize;
    }

    @Override
    public void onStateWillChange() {
        super.onStateWillChange();
        this.target.getMoveCd().setStepSize(this.target.getDefaultStepSize());
    }

    @Override
    public void onAdd() {
        this.target.removeState(this.stepSize > this.target.getDefaultStepSize() ? "SlowDown" : "SpeedUp");
        // If SlowDown or SpeedUp is removed, the stepSize will be set to default since `onStateWillChange` will be called. Thus, we have to reset it to `this.stepSize`.
        target.getMoveCd().setStepSize(this.stepSize);
    }

    
}
