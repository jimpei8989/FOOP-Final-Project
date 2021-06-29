package model.weapon;

import utils.Coordinate;
import utils.CoordinateUtils;
import utils.Direction;

public class BoxingGlove extends HarmingWeapon {
    private double range = 2.5, radian = Math.PI / 6;

    public BoxingGlove() {
        super();
    }

    public BoxingGlove(Coordinate coord) {
        super(coord, BoxingGlove.getDefaultDamage());
    }

    public BoxingGlove(Weapon weapon, Coordinate coord) {
        super(weapon, coord, BoxingGlove.getDefaultDamage());
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

    public static int getDefaultDamage() {
        return 30;
    }

    public double getRange() {
        return this.range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getRadian() {
        return this.radian;
    }

    public void setRadian(double radian) {
        this.radian = radian;
    }

    @Override
    public boolean inRange(Coordinate coord) {
        // A 60-degree sector with radius 2.5 units by default.
        Coordinate center = this.owner.getCoordinate();
        Coordinate facing = this.owner.getFacing().getCoord();
        Coordinate delta = CoordinateUtils.minus(coord, center);
        return CoordinateUtils.length(delta) <= this.range
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
        this.animateCoordinate = CoordinateUtils.scale(facing.getCoord(), this.range * progress);
        this.zoom = progress * this.range;
    }
}
