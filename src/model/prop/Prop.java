package model.prop;

import model.interfaces.*;
import model.Pacman;

public class Prop implements Locatable, Pickable, Tickable {
    Prop() {

    }

    // Locatable

    // Pickable
    public void onPickUp(Pacman p) {
    }

    // Tickable
    public void onTurnBegin() {
    }

    public void onTurnEnd() {
    }

    public void onRoundBegin() {
    }

    public void onRoundEnd() {
    }
}
