package model.prop;

import model.Pacman;

public abstract class PointProp extends Prop {
    private boolean isPicked = false;

    protected abstract int getPoint();

    // Pickable
    @Override
    public void onPickUp(Pacman p) {
        p.setScore(p.getScore() + this.getPoint());
    }

    // Tickable
    @Override
    public void onRoundBegin() {
    }

    @Override
    public void onRoundEnd() {
    }

    @Override
    public void onTurnBegin() {
    }

    @Override
    public void onTurnEnd() {
    }

    // Active
    @Override
    public boolean isActive() {
        return this.isPicked;
    }
}
