package controller;

import utils.Action;
import java.awt.event.KeyEvent;
import java.util.Map;

public class KeyboardController extends Controller {
    private KeyEvent latestEvent = null;
    private final Map<KeyEvent, Action> keyMapping;

    public KeyboardController(Map<KeyEvent, Action> keyMapping) {
        this.keyMapping = keyMapping;
    }

    public void addKeyboardEvent(KeyEvent event) {
        // TODO: define keyMapping somewhere else (?)
        if (this.keyMapping.containsKey(event)) {
            this.latestEvent = event;
        }
    }

    @Override
    public Action decide() {
        if (latestEvent == null) {
            return Action.NO_OP;
        } else {
            return this.keyMapping.get(this.latestEvent);
        }
    }
}
