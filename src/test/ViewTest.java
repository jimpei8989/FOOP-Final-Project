package test;

import org.junit.jupiter.api.Test;

import view.View;

public class ViewTest {
    @Test
    void testRender() throws InterruptedException {
        View view = new View();
        view.render();
        Thread.sleep(3000);
    }
}
