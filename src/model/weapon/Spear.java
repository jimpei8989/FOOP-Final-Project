package model.weapon;

import utils.Coordinate;
import utils.CoordinateUtils;
import utils.Direction;

public class Spear extends HarmingWeapon {
    private double frontRange = 4;
    private double sideRange = 0.5;

    public Spear() {
        super();
    }

    public Spear(Coordinate coord) {
        super(coord, getDefaultDamage());
    }

    public Spear(Weapon weapon, Coordinate coord) {
        super(weapon, coord, getDefaultDamage());
    }

    @Override
    public Weapon copy(Coordinate coord) {
        return new Spear(this, coord);
    }

    @Override
    public String getName() {
        return "spear";
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

    public double getFrontRange() {
        return this.frontRange;
    }

    public void setFrontRange(double frontRange) {
        this.frontRange = frontRange;
    }

    public double getSideRange() {
        return this.sideRange;
    }

    public void setSideRange(double sideRange) {
        this.sideRange = sideRange;
    }

    public static int getDefaultDamage() {
        return 30;
    }

    @Override
    public boolean inRange(Coordinate coord) {
        // The rectangle with 4 units long and 1 unit wide by default.
        Coordinate center = this.owner.getCoordinate();
        Coordinate facing = this.owner.getFacing().getCoord();
        Coordinate delta = CoordinateUtils.minus(coord, center);
        double frontDist = CoordinateUtils.dotProduct(facing, delta);
        return frontDist <= this.frontRange && frontDist >= 0
                && Math.sqrt(Math.pow(CoordinateUtils.length(delta), 2) - Math.pow(frontDist, 2)) <= this.sideRange;
    }

    @Override
    public void calculateAnimate() {
        Direction facing = this.owner.getFacing();
        double originDegree = facing == Direction.RIGHT ? 0
                : facing == Direction.DOWN ? 90 : facing == Direction.LEFT ? 180 : 270;

        double progress = 0;

        if (this.getWeaponState() == WeaponState.preAttack) {
            progress = Math.min(this.preAttackCd.getPercent() * 3, 1);
        } else if (this.getWeaponState() == WeaponState.realAttack) {
            progress = 1;
        } else if (this.getWeaponState() == WeaponState.postAttack) {
            progress = Math.min((1 - this.postAttackCd.getPercent()) * 3, 1);
        }

        this.zoom = progress;

        double stretching = 0;

        if (this.getWeaponState() == WeaponState.preAttack) {
            stretching = this.preAttackCd.getPercent();
        } else if (this.getWeaponState() == WeaponState.realAttack) {
            stretching = 1;
        } else if (this.getWeaponState() == WeaponState.postAttack) {
            stretching = 1 - this.postAttackCd.getPercent();
        }

        this.degree = originDegree;
        this.animateCoordinate = CoordinateUtils.scale(facing.getCoord(), this.frontRange * stretching);
    }
}
