package test;

import org.junit.jupiter.api.Test;

import model.Pacman;
import model.state.Berserker;
import model.weapon.Sword;
import utils.Coordinate;
import view.View;
import view.PacmanRenderer;

public class BerserkerTest {
    private TestUtils utils = new TestUtils();

    @Test
    void testBerserker() throws InterruptedException {
        Pacman attacker = utils.fakePacman(new Coordinate(1, 1)), sufferer = utils.fakePacman(new Coordinate(1, 1));

        Sword sword1 = new Sword(new Coordinate(0, 0)), sword2 = new Sword(new Coordinate(0, 0));
        sword1.onPickUp(attacker);
        sword2.onPickUp(sufferer);

        int originalDamage = sword1.getDamage();
        attacker.addState(new Berserker(attacker));
        int fullHP = sufferer.getHP();
        attacker.attack();
        sword1.onAttackSuccess(sufferer);
        assert sufferer.getHP() == fullHP - originalDamage * 2;
    }
}
