package model.weapon;

import model.Pacman;
import model.state.State;
import model.state.Stunned;
import utils.Coordinate;
import utils.CoordinateUtils;

public class Explosion extends StateChangingWeapon {
    private double range = 3;
    private static Pacman fakeMan = new Pacman("Fake Pacman", 0, 1000, 0, 10, new Coordinate(0, 0));

    public Explosion() {
        super();
    }

    public Explosion(Coordinate coord) {
        super(coord, getDefaultState());
    }

    public Explosion(Weapon weapon, Coordinate coord) {
        super(weapon, coord, getDefaultState());
    }

    @Override
    public Weapon copy(Coordinate coord) {
        return new Explosion(this, coord);
    }

    @Override
    public String getName() {
        return "explosion";
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

    public static State getDefaultState() {
        return new Stunned(fakeMan);
    }

    @Override
    public boolean inRange(Coordinate coord) {
        // The circle with radius 3 units by default.
        Coordinate center = this.owner.getCoordinate();
        // Coordinate facing = this.owner.getFacing().getCoord();
        Coordinate delta = CoordinateUtils.minus(coord, center);
        return CoordinateUtils.length(delta) <= this.range;
    }

    @Override
    public void calculateAnimate() {

        double progress = 0;


        if (this.getWeaponState() == WeaponState.preAttack) {
            progress = this.preAttackCd.getPercent();
        } else if (this.getWeaponState() == WeaponState.realAttack) {
            progress = 1;
        } else if (this.getWeaponState() == WeaponState.postAttack) {
            progress = 1 - this.postAttackCd.getPercent();
        }

        this.zoom = progress * this.range;

        this.degree = 0;
        this.animateCoordinate = new Coordinate(0, 0);
    }
}
