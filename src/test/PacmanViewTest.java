package test;

import org.junit.jupiter.api.Test;

import model.Pacman;
import utils.Coordinate;
import view.View;
import view.PacmanRenderer;

public class PacmanViewTest {
    @Test
    void testRender() throws InterruptedException {
        View view = new View();
        Coordinate coordinate = new Coordinate(10, 20);
        view.addPacmanRenderer(new PacmanRenderer(new Pacman("Dylan", 0, 300, 300, 2, coordinate), 10));
        int counter = 0;
        while (counter < 3000) {
            view.render();
            counter += 100;
            Thread.sleep(100);
        }
    }
}
