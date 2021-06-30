package controller;

import utils.Action;
import java.awt.event.KeyEvent;
import java.util.Map;

import model.Pacman;
import model.World;

public class KeyboardController extends Controller {
    private int latestEvent = -1;
    private final Map<Integer, Action> keyMapping;

    public KeyboardController(Map<Integer, Action> keyMapping) {
        this.keyMapping = keyMapping;
    }

    public void addKeyboardEvent(KeyEvent event) {
        int eventCode = event.getKeyCode();
        if (this.keyMapping.containsKey(eventCode)) {
            this.latestEvent = eventCode;
        }
    }

    @Override
    public Controller copy(Pacman target, World world) {
        return new KeyboardController(this.keyMapping);
    }

    @Override
    public Action decide() {
        if (!this.keyMapping.containsKey(latestEvent)) {
            return Action.NO_OP;
        } else {
            return this.keyMapping.get(this.latestEvent);
        }
    }
}
