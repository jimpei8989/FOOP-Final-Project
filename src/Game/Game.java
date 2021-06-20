package Game;
import java.util.List;

import model.interfaces.Tickable;
import model.World;
import view.Renderable;
import view.View;

public class Game {
    private boolean running;
    private View view;
    private World world;

    public Game(View view) {
        this.world = new World(this);
        this.view = view;
    }

    public void start() {
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        running = true;
        while (running) {
            world.update();
            view.render();
            delay(1);
        }
    }

    private void delay(int ticks) {
        try {
            Thread.sleep(ticks * 33); // 1/30 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addMapRender(Renderable mapRenders) {
        view.addMapRender(mapRenders);
    }

    public void addPacmanRender(Renderable pacmanRenders) {
        view.addPacmanRender(pacmanRenders);
    }

    public void addObjectRender(Renderable objectRenders) {
        view.addObjectRender(objectRenders);
    }
}
