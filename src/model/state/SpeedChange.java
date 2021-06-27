package model.state;

import model.Pacman;
import model.utils.CoolDown;

public class SpeedChange extends State {
    CoolDown coolDown;
    int speedChange;

    public SpeedChange(Pacman target, int speedChange) {
        super("SpeedChange", target, 2 * 60);
        this.speedChange = speedChange;
        this.coolDown = new CoolDown(target.getDefaultMoveCoolDown() + speedChange);
        this.coolDown.reset((int) (this.coolDown.getInterval() * (1 - this.target.getMoveCd().getPercent())));
    }

    public SpeedChange(Pacman target, int speedChange, int turn) {
        super("SpeedChange", target, turn);
        this.speedChange = speedChange;
        this.coolDown = new CoolDown(target.getDefaultMoveCoolDown() + speedChange);
        this.coolDown.reset((int) (this.coolDown.getInterval() * (1 - this.target.getMoveCd().getPercent())));
    }

    @Override
    public void onTurnBegin() {
        super.onTurnBegin();
        if (this.target.getMoveCd() != this.coolDown) {
            this.coolDown.reset((int) (this.coolDown.getInterval() * (1 - this.target.getMoveCd().getPercent())));
            this.target.setMoveCd(this.coolDown);
        }
    }

    @Override
    public void onStateWillChange() {
        super.onStateWillChange();
        this.target.setMoveCd(((Normal) this.target.getStateByName("Normal")).getCoolDown());
    }

}
