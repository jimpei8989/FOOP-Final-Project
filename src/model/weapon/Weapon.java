package model.weapon;

import model.Pacman;
import model.interfaces.*;
import model.utils.CoolDown;
import utils.Active;
import utils.Coordinate;

/*
 * A weapon has several states, as defined in WeaponState.
 */

public abstract class Weapon implements Active, Tickable, Locatable, Pickable {
    private Coordinate coord = new Coordinate(0, 0);
    private WeaponState state = WeaponState.onGround;

    protected final CoolDown cd;
    protected final CoolDown preAttackCd;
    protected final CoolDown postAttackCd;

    protected Pacman owner;

    // animate
    protected final CoolDown animateCd;
    protected Coordinate animateCoordinate;
    protected double zoom = 1;
    protected double degree = 0;

    Weapon() {
        this.cd = new CoolDown(this.getDefaultCoolDown());
        this.preAttackCd = new CoolDown(this.getDefaultPreAttackCoolDown());
        this.postAttackCd = new CoolDown(this.getDefaultPostAttackCoolDown());
        this.animateCd = new CoolDown(this.getDefaultAnimateCoolDown());
    }

    Weapon(Coordinate coord) {
        this.coord = coord;
        this.cd = new CoolDown(this.getDefaultCoolDown());
        this.preAttackCd = new CoolDown(this.getDefaultPreAttackCoolDown());
        this.postAttackCd = new CoolDown(this.getDefaultPostAttackCoolDown());
        this.animateCd = new CoolDown(this.getDefaultAnimateCoolDown());
    }

    // copy constructor
    Weapon(Weapon weapon, Coordinate coord) {
        this.coord = coord;
        this.cd = new CoolDown(weapon.getDefaultCoolDown());
        this.preAttackCd = new CoolDown(weapon.getDefaultPreAttackCoolDown());
        this.postAttackCd = new CoolDown(weapon.getDefaultPostAttackCoolDown());
        this.animateCd = new CoolDown(weapon.getDefaultAnimateCoolDown());
    }

    public abstract Weapon copy(Coordinate coord);

    public abstract String getName();

    // Weapon
    public WeaponState getWeaponState() {
        return this.state;
    }

    protected abstract int getDefaultCoolDown();

    protected abstract int getDefaultPreAttackCoolDown();

    protected abstract int getDefaultPostAttackCoolDown();

    public void use() {
        this.state = WeaponState.preAttack;
        this.preAttackCd.reset();
        this.animateCd.reset();
        this.calculateAnimate();
    }

    protected int getDefaultAnimateCoolDown() {
        return this.getDefaultPreAttackCoolDown() + 1 + this.getDefaultPostAttackCoolDown();
    }

    // Checks if a coord (realCoordinate) can be attacked by the weapon
    public abstract boolean inRange(Coordinate coord);

    public abstract void onAttackSuccess(Pacman target);

    public boolean canAttack() {
        return this.state == WeaponState.ready;
    }

    public boolean isAttacking() {
        return this.state == WeaponState.preAttack || this.state == WeaponState.realAttack
                || this.state == WeaponState.postAttack;
    }

    // Active
    public boolean isActive() {
        return this.state != WeaponState.dropped;
    }

    // Tickable
    @Override
    public void onRoundBegin() {}

    @Override
    public void onRoundEnd() {}

    @Override
    public void onTurnBegin() {
        if (!this.animateCd.available()) {
            this.calculateAnimate();
        }
    }

    @Override
    public void onTurnEnd() {
        switch (this.state) {
            case cooldown:
                this.cd.update();
                if (this.cd.available()) {
                    this.state = WeaponState.ready;
                }
                break;
            case preAttack:
                this.preAttackCd.update();
                if (this.preAttackCd.available()) {
                    this.state = WeaponState.realAttack;
                }
                break;
            case realAttack:
                this.state = WeaponState.postAttack;
                this.postAttackCd.reset();
                break;
            case postAttack:
                this.postAttackCd.update();
                if (this.postAttackCd.available()) {
                    this.state = WeaponState.cooldown;
                    this.cd.reset();
                }
                break;
            default:
                break;
        }
        this.animateCd.update();
    }

    // Locatable
    public Coordinate getCoordinate() {
        return this.coord;
    }

    public void setCoordinate(Coordinate coord) {
        throw new RuntimeException("You should not setCoordinate to a weapon");
    }

    public Coordinate getRealCoordinate() {
        if (this.state == WeaponState.onGround) {
            return this.getCoordinate();
        } else if (this.state != WeaponState.dropped) {
            return this.owner.getRealCoordinate().add(this.animateCoordinate);
        } else {
            throw new RuntimeException("You should not ask the coordinate of a inactive weapon");
        }
    }

    // For animate
    public boolean needToRender() {
        return this.state == WeaponState.onGround || this.state == WeaponState.preAttack
                || this.state == WeaponState.realAttack || this.state == WeaponState.postAttack;
    }

    public abstract void calculateAnimate();

    public double getDegree() {
        return this.degree;
    }

    public double getZoom() {
        return this.zoom;
    }

    // Pickable
    @Override
    public void onPickUp(Pacman p) {
        this.state = WeaponState.ready;
        this.owner = p;
        this.owner.onWeaponGet(this);
    }

    public void onDrop(Pacman p) {
        this.state = WeaponState.dropped;
    }
}
