package model.weapon;

import javax.swing.BoxLayout;

import model.Pacman;
import utils.Coordinate;
import utils.CoordinateUtils;
import utils.Direction;

public class BoxingGlove extends Weapon {
    private double range, radian;
    private int damage;

    public BoxingGlove(Coordinate coord) {
        super(coord);
        this.range = getDefaultRange();
        this.radian = getDefaultRadian();
        this.damage = getDefaultDamage();
    }

    public BoxingGlove(Weapon weapon, Coordinate coord) {
        super(weapon, coord);
        this.range = getDefaultRange();
        this.radian = getDefaultRadian();
        this.damage = getDefaultDamage();
    }

    @Override
    public Weapon copy(Coordinate coord) {
        return new BoxingGlove(this, coord);
    }

    @Override
    public String getName() {
        return "boxing-glove";
    }

    @Override
    public void onAttackSuccess(Pacman target) {
        target.setHP(target.getHP() - this.getDamage());
    }

    @Override
    public int getDefaultCoolDown() {
        return 60;
    }

    @Override
    public int getDefaultPreAttackCoolDown() {
        return 5;
    }

    @Override
    public int getDefaultPostAttackCoolDown() {
        return 5;
    }

    public double getDefaultRange() {
        return 1.5;
    }

    public double getRange() {
        return this.range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getDefaultRadian() {
        return Math.PI / 6;
    }

    public double getRadian() {
        return this.radian;
    }

    public void setRadian(double radian) {
        this.radian = radian;
    }

    public int getDefaultDamage() {
        return 30;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean inRange(Coordinate coord) {
        // A 60-degree sector with radius 1.5 units by default.
        Coordinate center = this.owner.getCoordinate();
        Coordinate facing = this.owner.getFacing().getCoord();
        Coordinate delta = CoordinateUtils.minus(coord, center);
        return CoordinateUtils.length(delta) <= this.getRange()
                && CoordinateUtils.dotProduct(facing, CoordinateUtils.norm(delta)) >= Math.cos(this.getRadian());
    }

    @Override
    public void calculateAnimate() {
        Direction facing = this.owner.getFacing();
        double originDegree = facing == Direction.RIGHT ? 0
                : facing == Direction.DOWN ? 90 : facing == Direction.LEFT ? 180 : 270;

        double progress = 0;

        if (this.getWeaponState() == WeaponState.preAttack) {
            progress = this.preAttackCd.getPercent();
        } else if (this.getWeaponState() == WeaponState.realAttack) {
            progress = 1;
        } else if (this.getWeaponState() == WeaponState.postAttack) {
            progress = 1 - this.postAttackCd.getPercent();
        }

        this.degree = originDegree;
        this.animateCoordinate = CoordinateUtils.scale(facing.getCoord(), this.getRange() * progress);
        this.zoom = progress;
    }
}
