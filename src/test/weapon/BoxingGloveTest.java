package test.weapon;

import utils.Coordinate;

import org.junit.Test;

import model.Pacman;
import model.weapon.BoxingGlove;

public class BoxingGloveTest {
    private TestUtils utils = new TestUtils();

    @Test
    public void testFunctionality() {
        BoxingGlove weapon = new BoxingGlove(new Coordinate(0, 0));
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
        BoxingGlove weapon = new BoxingGlove(new Coordinate(0, 0));
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1));
        weapon.onPickUp(attacker);

        assert weapon.inRange(new Coordinate(1.0, 2.0));
        assert weapon.inRange(new Coordinate(1.0, 2.5));
        assert weapon.inRange(new Coordinate(0.50, 2.0));
        assert weapon.inRange(new Coordinate(1.50, 2.0));
    }

    @Test
    public void testRangeFail() {
        BoxingGlove weapon = new BoxingGlove(new Coordinate(0, 0));
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1));
        weapon.onPickUp(attacker);

        assert !weapon.inRange(new Coordinate(2.0, 1.0));
        assert !weapon.inRange(new Coordinate(0.0, 1.0));
        assert !weapon.inRange(new Coordinate(1.0, 2.51));
        assert !weapon.inRange(new Coordinate(1.0, 3.0));

        // Hint: tan(30-degree) \approx 0.57735
        assert !weapon.inRange(new Coordinate(0.4, 2.0));
        assert !weapon.inRange(new Coordinate(1.6, 2.0));
    }
}
