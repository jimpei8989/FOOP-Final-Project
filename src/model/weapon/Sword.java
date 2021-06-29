package model.weapon;

import utils.Coordinate;
import utils.CoordinateUtils;
import utils.Direction;

public class Sword extends HarmingWeapon {
    private double range = 1;

    public Sword() {
        super();
    }

    public Sword(Coordinate coord) {
        super(coord, getDefaultDamage());
    }

    public Sword(Weapon weapon, Coordinate coord) {
        super(weapon, coord, getDefaultDamage());
    }

    @Override
    public Weapon copy(Coordinate coord) {
        return new Sword(this, coord);
    }

    @Override
    public String getName() {
        return "sword";
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

    public double getRange() {
        return this.range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public static int getDefaultDamage() {
        return 30;
    }

    @Override
    public boolean inRange(Coordinate coord) {
        // The circle with radius 1 units by default.
        Coordinate center = this.owner.getCoordinate();
        // Coordinate facing = this.owner.getFacing().getCoord();
        Coordinate delta = CoordinateUtils.minus(coord, center);
        return CoordinateUtils.length(delta) <= this.range;
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

        double turning = this.animateCd.getPercent();

        this.degree = 135 - 540 * turning + originDegree; // It looks even better on turning 540
                                                          // degrees.
        this.animateCoordinate = CoordinateUtils.fromPolar(this.range * progress, this.degree - 90);
    }
}
