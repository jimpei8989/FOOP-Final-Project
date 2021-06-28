package test.weapon;

import utils.Coordinate;
import utils.Direction;
import view.PacmanRenderer;
import view.View;
import view.WeaponRenderer;

import org.junit.Test;

import model.Pacman;
import model.weapon.BoxingGlove;
import model.weapon.Explosion;
import model.weapon.Spear;
import model.weapon.Sword;
import model.weapon.Weapon;

public class WeaponViewTest {
    private TestUtils utils = new TestUtils();

    @Test
    public void testRender() throws InterruptedException {
        View view = new View();
        Weapon sword = new Sword(new Coordinate(0, 1));
        view.addObjectRenderer(new WeaponRenderer(sword, 20));
        Weapon boxingGlove = new BoxingGlove(new Coordinate(1, 0));
        view.addObjectRenderer(new WeaponRenderer(boxingGlove, 20));
        Weapon spear = new Spear(new Coordinate(1, 1));
        view.addObjectRenderer(new WeaponRenderer(spear, 20));
        Pacman attacker = utils.fakePacman(new Coordinate(0, 0));
        view.addPacmanRenderer(new PacmanRenderer(attacker, 20));

        int counter = 0;
        while (counter < 3000) {
            view.render();
            counter += 100;
            Thread.sleep(100);
        }
    }

    @Test
    public void testAttack() throws InterruptedException {
        View view = new View();
        // Weapon weapon = new Sword(new Coordinate(5, 6));
        // Weapon weapon = new BoxingGlove(new Coordinate(6, 5));
        // Weapon weapon = new Spear(new Coordinate(6, 6));
        Weapon weapon = new Explosion(new Coordinate(7, 7));
        view.addObjectRenderer(new WeaponRenderer(weapon, 20));
        Pacman attacker = utils.fakePacman(new Coordinate(5, 5));
        attacker.setFacing(Direction.LEFT);
        view.addPacmanRenderer(new PacmanRenderer(attacker, 20));

        int counter = 0;
        while (counter < 3000) {
            if (counter == 1000) {
                weapon.onPickUp(attacker);
                weapon.use();
            }
            weapon.onTurnBegin();

            view.render();
            weapon.onTurnEnd();
            counter += 100;
            Thread.sleep(100);
        }
    }
}
