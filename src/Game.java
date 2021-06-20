import java.util.List;

import model.Tickable;
import model.World;

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

    public void addMapRender(Renderable map_renders) {
        view.addMapRenders(map_renders);
    }

    public void addPacmanRender(List<Renderable> pacman_renders) {
        view.addRenderableRender(pacman_renders);
    }

    public void addObjectRender(List<Renderable> object_renders) {
        view.addObjectRender(object_renders);
    }
}
