package model.state;

import model.Pacman;

public class Normal extends State {
    public Normal(Pacman target) {
        super("Normal", target);
    }

    @Override
    public void onTurnEnd() {
    }

}
