package test;

import org.junit.jupiter.api.Test;

import model.Pacman;
import utils.Coordinate;
import view.View;
import view.PacmanRenderer;

public class DeadAndReviveTest {
    @Test
    void testRender() throws InterruptedException {
        View view = new View();
        Coordinate coordinate = new Coordinate(10, 20);
        Pacman sufferer = new Pacman("Dylan", 0, 300, 300, 2, coordinate);
        view.addPacmanRenderer(new PacmanRenderer(sufferer, 10));
        int counter = 0;
        while (counter < 10000) {
            sufferer.onTurnBegin();
            if (counter % 100 == 0) {
                sufferer.takeDamage(null, 40);
            }
            if (counter % 100 == 0) {
                System.out.println(sufferer.getHP());
            }
            view.render();
            sufferer.onTurnEnd();
            sufferer.onRoundEnd();
            counter += 20;
            Thread.sleep(20);
        }
    }
}
