package model;

import controller.Controller;
import model.interfaces.*;
import model.state.State;
import model.utils.CoolDown;
import model.prop.Prop;
import model.weapon.Weapon;
import utils.Action;
import utils.Active;
import utils.Coordinate;
import utils.CoordinateUtils;
import utils.Direction;

import java.util.ArrayList;
import java.util.List;

public class Pacman implements Locatable, Tickable, Active {
    private final String name;
    private int ID, HP, score, tickPerGrid;
    private Coordinate coordinate;
    private List<State> states = new ArrayList<>();
    private Weapon weapon;
    private Direction facing;

    private Controller controller;
    private final CoolDown moveCd;

    public Pacman(String name, int id, int hp, int score, int tickPerGrid, Coordinate coordinate) {
        this.name = name;
        this.ID = id;
        this.HP = hp;
        this.score = score;
        this.tickPerGrid = tickPerGrid;
        this.coordinate = coordinate;
        this.facing = Direction.RIGHT;
        this.moveCd = new CoolDown(this.getDefaultMoveCoolDown());
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public void setHP(int hp) {
        this.HP = hp;
    }

    public int getHP() {
        return this.HP;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public void setTickPerGrid(int tickPerGrid) {
        this.tickPerGrid = tickPerGrid;
    }

    public int getTickPerGrid() {
        return this.tickPerGrid;
    }

    public Direction getFacing() {
        return this.facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public boolean canDecide() {
        return !this.isMoving() && !this.isAttacking();
    }

    public boolean canAttack() {
        return this.weapon != null && this.weapon.canAttack();
    }

    public int getDefaultMoveCoolDown() {
        return 10;
    }

    public void move(Direction direction) {
        this.facing = direction;
        this.moveCd.reset();
    }

    public boolean isMoving() {
        return !this.moveCd.available();
    }

    public void attack() {
        this.weapon.use();
    }

    public boolean isAttacking() {
        return this.weapon != null && this.weapon.isAttacking();
    }

    // Cotroller
    public Action decide() {
        return this.controller.decide();
    }
    
    // State related
    public void addState(State state) {
        this.states.add(state);
    }

    public void removeStates() {
        this.states.removeIf(s -> !s.isActive());
    }

    public void onPropGet(Prop prop) {
    }

    public void onWeaponGet(Weapon weapon) {
        this.weapon = weapon;
    }

    // Locatable
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getRealCoordinate() {
        if (!this.isMoving()) {
            return this.getCoordinate();
        } else {
            return this.getCoordinate().add(CoordinateUtils.scale(this.facing.getCoord(), this.moveCd.getPercent()));
        }
    }

    // Tickable
    public void onTurnBegin() {
    }

    public void onTurnEnd() {
        if (!this.moveCd.available()) {
            this.moveCd.update();
            if (this.moveCd.available()) {
                this.coordinate = this.coordinate.add(this.facing.getCoord());
            }
        }

        System.out.printf("%d > %s\n", this.ID, this.getRealCoordinate().toString());
    }

    public void onRoundBegin() {
    }

    public void onRoundEnd() {
    }

    // Active
    public boolean isActive() {
        return true;
    }

    public void addController(Controller controller) {
        this.controller = controller;
    }
}
