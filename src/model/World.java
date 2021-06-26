package model;

import java.util.List;

import Game.Game;
import model.interfaces.Tickable;
import model.map.Map;
import view.Renderable;

public class World {
    private Game game;
    private Map map;
    private List<Pacman> pacmans;
    private List<Tickable> objects;

    public World(Game game) {
        this.game = game;
    }

    public World(Game game, Map map, List<Pacman> pacmans, List<Tickable> objects) {
        this.game = game;
        this.map = map;
        this.pacmans = pacmans;
        this.objects = objects;
    }

    public void update() {
        for (Pacman pacman : pacmans)
            pacman.onRoundBegin();
        for (Tickable object : objects)
            object.onRoundBegin();
        for (Pacman pacman : pacmans)
            pacman.onRoundEnd();
        for (Tickable object : objects)
            object.onRoundEnd();
    }

    public void addMapRenderer(Renderable mapRenders) {
        this.game.addMapRenderer(mapRenders);
    }

    public void addPacmanRenderer(Renderable pacmanRenders) {
        this.game.addPacmanRenderer(pacmanRenders);
    }

    public void addObjectRenderer(Renderable objectRenders) {
        this.game.addObjectRenderer(objectRenders);
    }
}
