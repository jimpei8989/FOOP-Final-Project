package model.prop;

import model.Pacman;

public class BigPointProp extends Prop {
    private static int point = 5;

    // Pickable
    @Override
    public void onPickUp(Pacman p) {
        p.setScore(p.getScore() + point);
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
        return false;
    }
}
