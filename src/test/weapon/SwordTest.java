package test.weapon;

import utils.Coordinate;

import org.junit.Test;

import model.Pacman;
import model.weapon.Sword;

public class SwordTest {
    private TestUtils utils = new TestUtils();

    @Test
    public void testFunctionality() {
        Sword weapon = new Sword(new Coordinate(0, 0));
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1));
        assert !weapon.canAttack();
        weapon.onPickUp(attacker);
        assert weapon.canAttack();
        weapon.use();
        assert !weapon.canAttack();

        Pacman sufferer = utils.fakePacman(new Coordinate(1, 1));
        weapon.onAttackSuccess(sufferer);
        assert sufferer.getHP() == 1000 - weapon.getDamage();
    }

    @Test
    public void testRangeSuccess() {
        Sword weapon = new Sword(new Coordinate(0, 0));
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1));
        weapon.onPickUp(attacker);

        assert weapon.inRange(new Coordinate(1.5, 1.5));
        assert weapon.inRange(new Coordinate(1.5, 0.5));
        assert weapon.inRange(new Coordinate(0.5, 0.5));
        assert weapon.inRange(new Coordinate(0.5, 1.5));
    }

    @Test
    public void testRangeFail() {
        Sword weapon = new Sword(new Coordinate(0, 0));
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1));
        weapon.onPickUp(attacker);

        assert !weapon.inRange(new Coordinate(1.8, 1.8));
        assert !weapon.inRange(new Coordinate(1.8, 0.2));
        assert !weapon.inRange(new Coordinate(0.2, 0.2));
        assert !weapon.inRange(new Coordinate(0.2, 1.8));
    }
}
