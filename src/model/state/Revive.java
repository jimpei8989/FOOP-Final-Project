package model.state;

import model.Pacman;
import model.utils.CoolDown;

public class Revive extends State {
    private boolean needToRender;
    private CoolDown coolDown;

    public Revive(Pacman target) {
        super("Revive", target, 150);
        this.needToRender = true;
        this.coolDown = new CoolDown(10);
        this.coolDown.reset();
    }

    @Override
    public void onTurnEnd() {
        super.onTurnEnd();
        if (!this.coolDown.available()) {
            this.coolDown.update();
            if (this.coolDown.available()) {
                this.needToRender = !this.needToRender;
                this.coolDown.reset();
            }
        }
    }

    @Override
    public boolean needToRender() {
        return this.needToRender;
    }
}
