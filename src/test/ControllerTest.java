package test;

import org.junit.jupiter.api.Test;

import controller.*;
import utils.Action;
import view.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class ControllerTest {
    @Test
    void testup() throws InterruptedException {
        View view = new View();
        Map<Integer, Action> keyMapping = new HashMap<>();
        keyMapping.put(KeyEvent.VK_UP, Action.UP);
        keyMapping.put(KeyEvent.VK_DOWN, Action.DOWN);
        keyMapping.put(KeyEvent.VK_LEFT, Action.LEFT);
        keyMapping.put(KeyEvent.VK_RIGHT, Action.RIGHT);
        keyMapping.put(KeyEvent.VK_SPACE, Action.ATTACK);
        KeyboardController controller = new KeyboardController(keyMapping);
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                controller.addKeyboardEvent(keyEvent);
            }
        });
        int counter = 0;
        while (counter < 10000) {
            view.render();
            Action action = controller.decide();
            System.out.println(action);
            counter += 100;
            Thread.sleep(100);
        }
    }

}
