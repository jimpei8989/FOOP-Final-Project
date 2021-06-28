package test.weapon;

import utils.Coordinate;

import org.junit.Test;

import model.Pacman;
import model.weapon.Spear;

public class SpearTest {
    private TestUtils utils = new TestUtils();

    @Test
    public void testFunctionality() {
        Spear weapon = new Spear(new Coordinate(0, 0));
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
        Spear weapon = new Spear(new Coordinate(0, 0));
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1));
        weapon.onPickUp(attacker);

        assert weapon.inRange(new Coordinate(1, 2));
        assert weapon.inRange(new Coordinate(1.2, 2.4));
        assert weapon.inRange(new Coordinate(0.6, 2.6));
        assert weapon.inRange(new Coordinate(1.1, 1.1));
    }

    @Test
    public void testRangeFail() {
        Spear weapon = new Spear(new Coordinate(0, 0));
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1));
        weapon.onPickUp(attacker);

        assert !weapon.inRange(new Coordinate(1.0, 3.2));
        assert !weapon.inRange(new Coordinate(1.0, 0.2));
        assert !weapon.inRange(new Coordinate(0.2, 1.2));
        assert !weapon.inRange(new Coordinate(1.8, 1.8));
    }
}
