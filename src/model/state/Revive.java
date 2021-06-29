package model.state;

import model.Pacman;
import model.interfaces.TakeDamageCallback;
import model.utils.CoolDown;

public class Revive extends State {
    private boolean needToRender;
    private CoolDown coolDown;
    private TakeDamageCallback notBeDamaged = new TakeDamageCallback() {
        public int onTakeDamage(int damage) {
            return 0;
        }
    };

    public Revive(Pacman target) {
        super("Revive", target, 150);
        this.needToRender = true;
        this.coolDown = new CoolDown(10);
        this.coolDown.reset();
    }

    public Revive(State state, Pacman target) {
        super(state, target);
        this.needToRender = true;
        this.coolDown = new CoolDown(10);
        this.coolDown.reset();
    }

    @Override
    public void onAdd() {
        this.target.addTakeDamageCallback(this.notBeDamaged);
    }

    @Override
    public State copy(Pacman target) {
        return new Revive(this, target);
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

    @Override
    public void onStateWillChange() {
        this.target.removeTakeDamageCallback(this.notBeDamaged);
    }
}
