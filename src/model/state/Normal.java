package model.state;

import model.Pacman;
import model.utils.CoolDown;

public class Normal extends State {
    private CoolDown coolDown;

    public Normal(Pacman target) {
        super("Normal", target);
        this.coolDown = target.getMoveCd();
    }

    public CoolDown getCoolDown() {
        return this.coolDown;
    }

    @Override
    public void onTurnBegin() {
        super.onTurnBegin();
        this.coolDown.reset((int) (this.coolDown.getInterval() * (1 - this.target.getMoveCd().getPercent())));
    }

    @Override
    public void onTurnEnd() {
    }

}
