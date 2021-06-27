package model;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Game.Game;
import model.interfaces.Locatable;
import model.interfaces.Pickable;
import model.interfaces.Tickable;
import model.map.Map;
import model.prop.SmallPointProp;
import model.weapon.Weapon;
import model.weapon.BoxingGlove;
import model.weapon.Sword;
import model.weapon.Weapon;
import model.weapon.WeaponState;
import utils.Action;
import utils.Coordinate;
import view.PropRenderer;
import view.Renderable;
import view.WeaponRenderer;

public class World {
    private Game game;
    private Map map;
    private List<Pacman> pacmans;
    private List<Tickable> objects;
    private java.util.Map<Coordinate, Locatable> coordsWithItems = new HashMap<>();
    private final Random random;

    public World(Game game, Map map, List<Pacman> pacmans, List<Tickable> objects) {
        this.game = game;
        this.map = map;
        this.pacmans = pacmans;
        this.objects = objects;
        this.random = new Random();
    }

    void generateProps() {
        for (Coordinate coordinate : this.map.getRoadCoords()) {
            if (!this.coordsWithItems.containsKey(coordinate)) {
                if (random.nextInt(1000) < 1) {
                    SmallPointProp prop = new SmallPointProp(coordinate);
                    this.objects.add(prop);
                    addObjectRenderer(new PropRenderer(prop, this.game.getRenderRatio()));
                    this.coordsWithItems.put(coordinate, prop);
                }
            }
        }
    }

    void tick() {
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
        for (Pacman pacman : this.pacmans) {
            if (pacman.canDecide()) {
                Coordinate coord = pacman.getCoordinate();
                if (this.coordsWithItems.containsKey(coord)) {
                    Pickable object = (Pickable) this.coordsWithItems.get(coord);
                    object.onPickUp(pacman);
                    this.coordsWithItems.remove(coord);

                    System.err.printf("+ Pacman %d gets an %s.\n", pacman.getID(), object);
                }
            } else if (pacman.isAttacking()) {
                Weapon weapon = pacman.getWeapon();
                if (weapon.getWeaponState() == WeaponState.realAttack) {
                    for (Pacman other : this.pacmans) {
                        if (pacman == other)
                            continue;
                        if (weapon.inRange(other.getRealCoordinate())) {
                            System.err.printf("Pacman %d hits pacman %d\n", pacman.getID(),
                                    other.getID());
                            weapon.onAttackSuccess(other);
                        }
                    }
                }
            }
        }

        // 3) TODO: add some more props
        this.generateProps();

        // 4) add some weapon, appear once every 300 ticks on average
        if (random.nextInt(300) < 1) {
            int cnt = this.map.getRoadCoords().size() - this.coordsWithItems.size();
            if (cnt > 0) {
                int choice = random.nextInt(cnt);
                for (Coordinate coordinate : this.map.getRoadCoords()) {
                    if (!this.coordsWithItems.containsKey(coordinate)) {
                        if (choice == 0) {
                            Weapon weapon = new BoxingGlove(coordinate);
                            this.objects.add(weapon);
                            addObjectRenderer(
                                    new WeaponRenderer(weapon, this.game.getRenderRatio()));
                            this.coordsWithItems.put(coordinate, weapon);
                            break;
                        }
                        choice -= 1;
                    }
                }
            }
        }

        for (Tickable object : objects) {
            object.onTurnBegin();
            object.onTurnEnd();
        }
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
