package model;

import java.util.List;

import Game.Game;
import model.interfaces.Tickable;
import model.map.Map;
import utils.Action;
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

    private void tick() {
        // 1) Each pacman decide and move
        for (Pacman pacman : this.pacmans) {
            pacman.onTurnBegin();

            if (pacman.canDecide()) {
                Action action = pacman.decide();

                if (action == Action.NO_OP) {
                    // do nothing
                } else if (action.getDirection() != null) {
                    // is a kind of direction
                    if (this.map.canPass(pacman.getCoordinate(), action.getDirection())) {
                        pacman.move(action.getDirection());
                    }
                } else if (action == Action.ATTACK) {
                    // attack
                    if (pacman.canAttack()) {
                        pacman.attack();
                    }
                }
            }

            pacman.onTurnEnd();
        }

        // 2) TODO: Finalize pacman's move

        // 3) TODO: add some more props
    }

    public void update() {
        for (Pacman pacman : pacmans)
            pacman.onRoundBegin();
        for (Tickable object : objects)
            object.onRoundBegin();

        this.tick();

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
