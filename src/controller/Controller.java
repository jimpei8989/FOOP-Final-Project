package controller;

import model.Helper;
import model.Pacman;
import model.World;
import utils.Action;

public abstract class Controller {
    protected final Pacman target;
    protected final Helper helper;

    public Controller() {
        this.target = null;
        this.helper = null;
    }

    public Controller(Pacman target, World world) {
        this.target = target;
        this.helper = new Helper(world);
    }

    public abstract Controller copy(Pacman target, World world);

    public abstract Action decide();
}
