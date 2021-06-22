package test;

import java.awt.Graphics;
import java.awt.Color;

import org.junit.jupiter.api.Test;

import view.Renderable;
import view.View;

class MyRenderer implements Renderable {
    private int counter = 0;

    @Override
    public void render(Graphics g) {
        counter += 100;
        g.setColor(Color.RED);
        g.fillOval(100, 100, 40, 40);
    }

    @Override
    public boolean isActive() {
        return counter < 1000;
    }

}

public class ViewTest {
    @Test
    void testRender() throws InterruptedException {
        View view = new View();
        view.addObjectRenderer(new MyRenderer());
        int counter = 0;
        while (counter < 3000) {
            view.render();
            counter += 100;
            Thread.sleep(100);
        }
    }
}
