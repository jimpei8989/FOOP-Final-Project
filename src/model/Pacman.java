package model;

import controller.Controller;
import model.interfaces.*;
import model.state.Dead;
import model.state.Normal;
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
import java.util.HashMap;
import java.util.Map;

public class Pacman implements Locatable, Tickable, Active {
    private final String name;
    private int ID, HP, fullHP, score;
    private Coordinate coordinate;
    private Map<String, State> states = new HashMap<>();
    private Weapon weapon;
    private Direction facing;
    private Coordinate nextCoord;

    private Controller controller;
    private CoolDown moveCd;

    private ArrayList<AttackCallback> attackCallbacks = new ArrayList<>();
    private ArrayList<TakeDamageCallback> takeDamageCallbacks = new ArrayList<>();
    private ArrayList<DeadCallback> deadCallbacks = new ArrayList<>();
    private ArrayList<DecideCallback> decideCallbacks = new ArrayList<>();
    private ArrayList<SwitchImageCallback> switchImageCallbacks = new ArrayList<>();

    public Pacman(String name, int id, int hp, int score, int tickPerGrid, Coordinate coordinate) {
        this.name = name;
        this.ID = id;
        this.HP = hp;
        this.fullHP = hp;
        this.score = score;
        this.coordinate = coordinate;
        this.facing = Direction.RIGHT;
        this.moveCd = new CoolDown(this.getDefaultStepSize(), this.getDefaultMoveCoolDown());
        Normal normalState = new Normal(this);
        this.addState(normalState);
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public void setHP(int hp) {
        this.HP = hp;
        if (this.isDead() && !this.states.containsKey("Dead")) {
            for (DeadCallback callback : deadCallbacks)
                callback.onDie(this);
            this.addState(new Dead(this));
        }
    }

    public int getFullHP() {
        return this.fullHP;
    }

    public void setFullHP() {
        this.HP = fullHP;
    }

    public int getHP() {
        return this.HP;
    }

    public boolean isDead() {
        return this.HP <= 0;
    }

    public void takeDamage(Pacman attacker, int damage) {
        for (TakeDamageCallback callback : this.takeDamageCallbacks)
            damage = callback.onTakeDamage(damage);
        int point = this.HP - damage > 0 ? damage / 4 : damage;
        attacker.setScore(attacker.getScore() + point);
        this.setScore(Math.max(this.getScore() - point, 0));
        this.setHP(this.HP - damage);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
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

    public CoolDown getMoveCd() {
        return this.moveCd;
    }

    public void setMoveCd(CoolDown coolDown) {
        this.moveCd = coolDown;
    }

    public final ArrayList<AttackCallback> getAttackCallbacks() {
        return this.attackCallbacks;
    }

    public void addAttackCallback(AttackCallback callback) {
        this.attackCallbacks.add(callback);
    }

    public void removeAttackCallback(AttackCallback callback) {
        this.attackCallbacks.remove(callback);
    }

    public final ArrayList<TakeDamageCallback> getTakeDamageCallbacks() {
        return this.takeDamageCallbacks;
    }

    public void addTakeDamageCallback(TakeDamageCallback callback) {
        this.takeDamageCallbacks.add(callback);
    }

    public void removeTakeDamageCallback(TakeDamageCallback callback) {
        this.takeDamageCallbacks.remove(callback);
    }

    public final ArrayList<DeadCallback> getDeadCallbacks() {
        return this.deadCallbacks;
    }

    public void addDeadCallback(DeadCallback callback) {
        this.deadCallbacks.add(callback);
    }

    public void removeDeadCallback(DeadCallback callback) {
        this.deadCallbacks.remove(callback);
    }

    public final ArrayList<DecideCallback> getDecideCallbacks() {
        return this.decideCallbacks;
    }

    public void addDecideCallback(DecideCallback callback) {
        this.decideCallbacks.add(callback);
    }

    public void removeDecideCallback(DecideCallback callback) {
        this.decideCallbacks.remove(callback);
    }

    public final ArrayList<SwitchImageCallback> getSwitchImageCallbacks() {
        return this.switchImageCallbacks;
    }

    public void addSwitchImageCallback(SwitchImageCallback callback) {
        this.switchImageCallbacks.add(callback);
    }

    public void removeSwitchImageCallback(SwitchImageCallback callback) {
        this.switchImageCallbacks.remove(callback);
    }

    public boolean canDecide() {
        return !this.isMoving() && !this.isAttacking() && !this.isDead();
    }

    public boolean canAttack() {
        return this.weapon != null && this.weapon.canAttack();
    }

    public int getDefaultMoveCoolDown() {
        return 60;
    }

    public int getDefaultStepSize() {
        return 5;
    }

    public void move(Direction direction, Coordinate nextCoord) {
        this.facing = direction;
        this.nextCoord = nextCoord;
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

    // Controller
    public Action decide() {
        Action action = this.controller.decide();
        for (DecideCallback callback : this.decideCallbacks)
            action = callback.onDecide(action);
        return action;
    }

    public final Map<String, State> getStates() {
        return this.states;
    }

    // State related
    public void addState(State state) {
        // restore the state's turn if already existed
        if (states.containsKey(state.getName()))
            states.get(state.getName()).restoreFullTurn();
        else {
            this.states.put(state.getName(), state);
            state.onAdd();
        }
    }

    public void removeState(String stateName) {
        if (states.containsKey(stateName)) {
            states.get(stateName).onStateWillChange();
            this.states.remove(stateName);
        }
    }

    public void removeNonActiveStates() {
        Map<String, State> newStates = new HashMap<>(states);
        for (State state : newStates.values()) {
            if (!state.isActive()) {
                state.onStateWillChange();
                states.remove(state.getName());
            }
        }
    }

    public void onPropGet(Prop prop) {}

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
            return this.coordinate;
        } else {
            if (this.coordinate.add(this.facing.getCoord()).equals(this.nextCoord)) {
                return this.coordinate.add(
                        CoordinateUtils.scale(this.facing.getCoord(), this.moveCd.getPercent()));
            } else {
                if (this.moveCd.getPercent() < 0.5) {
                    return this.coordinate;
                } else {
                    return this.nextCoord;
                }
            }
        }
    }

    // Tickable
    public void onTurnBegin() {
        for (State state : states.values()) {
            state.onTurnBegin();
        }
    }

    public void onTurnEnd() {
        for (State state : states.values())
            state.onTurnEnd();
        this.removeNonActiveStates();

        if (!this.moveCd.available()) {
            this.moveCd.update();
            if (this.moveCd.available()) {
                this.coordinate = this.nextCoord;
            }
        }
    }

    public void onRoundBegin() {}

    public void onRoundEnd() {

    }

    // Active
    public boolean isActive() {
        return true;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public boolean needToRender() {
        for (State state : states.values())
            if (!state.needToRender())
                return false;

        return true;
    }
}
