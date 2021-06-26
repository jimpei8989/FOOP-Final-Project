package Game;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Tickable;
import model.Pacman;
import model.World;
import model.map.Map;
import utils.Coordinate;
import view.MapRenderer;
import view.PacmanRenderer;
import view.Renderable;
import view.View;

public class Game {
    private boolean running;
    private int numPlayers, renderRatio;
    private View view;
    private World world;
    private List<Pacman> pacmans = new ArrayList<>();

    public Game(int numPlayers, int renderRatio, View view, Map map) {
        this.numPlayers = numPlayers;
        this.renderRatio = renderRatio;
        this.view = view;
        for (int i = 0; i < this.numPlayers; i++) {
            Pacman pacman = new Pacman("Fiona", i, 300, 300, 1, map.getPacmanInitCoords().get(i));
            this.pacmans.add(pacman);
            addPacmanRenderer(new PacmanRenderer(pacman, this.renderRatio));
        }
        addMapRenderer(new MapRenderer(map, this.renderRatio));
        this.world = new World(this, map, this.pacmans, new ArrayList<>());
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

    public void addMapRenderer(Renderable mapRenders) {
        view.addMapRenderer(mapRenders);
    }

    public void addPacmanRenderer(Renderable pacmanRenders) {
        view.addPacmanRenderer(pacmanRenders);
    }

    public void addObjectRenderer(Renderable objectRenders) {
        view.addObjectRenderer(objectRenders);
    }
}
