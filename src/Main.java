import view.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.event.KeyEvent;

import Game.Game;
import model.map.Map;
import model.prop.BigPointProp;
import model.prop.Prop;
import model.prop.SlowDownProp;
import model.prop.SmallPointProp;
import model.prop.SpeedUpProp;
import model.weapon.BoxingGlove;
import model.weapon.Spear;
import model.weapon.Sword;
import model.weapon.Weapon;
import utils.Action;
import utils.RandomCollection;

class Main {
    public static void main(String[] args) {
        System.out.printf("Hello FiOnaOpPai!\n");

        Map map;
        try {
            File mapFile = Path.of("maps/fiona.txt").toFile();
            map = Map.readMapFromFile(mapFile);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        int playerNums = 4;
        List<java.util.Map<Integer, Action>> keyControls = new ArrayList<>();
        java.util.Map<Integer, Action> keyMapping1 = new HashMap<>();
        keyMapping1.put(KeyEvent.VK_UP, Action.UP);
        keyMapping1.put(KeyEvent.VK_DOWN, Action.DOWN);
        keyMapping1.put(KeyEvent.VK_LEFT, Action.LEFT);
        keyMapping1.put(KeyEvent.VK_RIGHT, Action.RIGHT);
        keyMapping1.put(KeyEvent.VK_SPACE, Action.ATTACK);
        keyControls.add(keyMapping1);
        for (int i = 1; i < playerNums; i++)
            keyControls.add(null);

        RandomCollection<Prop> props = new RandomCollection<Prop>().add(40, new SmallPointProp())
                .add(20, new BigPointProp()).add(15, new SpeedUpProp()).add(15, new SlowDownProp());
        RandomCollection<Weapon> weapons = new RandomCollection<Weapon>().add(20, new BoxingGlove())
                .add(40, new Sword()).add(40, new Spear());

        int renderRatio = map.getMaxWidth() / map.getWidth();
        View view = new View(map.getWidth() * renderRatio, map.getHeight() * renderRatio, renderRatio * 4);
        Game game = new Game(playerNums, renderRatio, view, map, keyControls, props, weapons);
        game.start();
    }
}