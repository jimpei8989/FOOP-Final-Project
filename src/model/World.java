package model;

import java.util.List;

import Game.Game;
import model.maps.Map;
import utils.Renderable;

public class World {
    private Game game;
    private Map map;
    private List<Pacman> pacmans;
    private List<Tickable> objects;

    public World(Game game) {
        this.game = game;
    }

    public World(Map map, List<Pacman> pacmans) {
        this.game = game;
        this.map = map;
        this.pacmans = pacmans;
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

    public void addMapRender(Renderable mapRenders) {
        this.game.addMapRender(mapRenders);
    }

    public void addPacmanRender(Renderable pacmanRenders) {
        this.game.addPacmanRender(pacmanRenders);
    }

    public void addObjectRender(Renderable objectRenders) {
        this.game.addObjectRender(objectRenders);
    }
}
