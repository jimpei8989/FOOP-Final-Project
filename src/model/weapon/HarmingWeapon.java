package model.weapon;

import model.Pacman;
import model.interfaces.AttackCallback;
import utils.Coordinate;

public abstract class HarmingWeapon extends Weapon {
    private int damage = 0;

    public HarmingWeapon() {
        super();
    }

    HarmingWeapon(Coordinate coord, int damage) {
        super(coord);
        this.damage = damage;
    }

    public HarmingWeapon(Weapon weapon, Coordinate coord, int damage) {
        super(weapon, coord);
        this.damage = damage;
    }
    
    public int getDamage() {
        int damage = this.damage;
        for (AttackCallback callback : this.owner.getAttackCallbacks())
            damage = callback.onAttackBegin(damage);
        return damage;
    };

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void onAttackSuccess(Pacman target){
        target.takeDamage(this.owner, this.getDamage());
    }
}
