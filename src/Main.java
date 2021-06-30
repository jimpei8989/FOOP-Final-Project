import view.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import static java.util.Map.ofEntries;
import static java.util.Map.entry;

import Game.Game;
import controller.Controller;
import controller.KeyboardController;
import controller.RandomMoveController;
import controller.SimpleController;
import model.map.Map;
import model.prop.BigPointProp;
import model.prop.Prop;
import model.prop.SlowDownProp;
import model.prop.SmallPointProp;
import model.prop.SpeedUpProp;
import model.prop.WineProp;
import model.weapon.BoxingGlove;
import model.weapon.Explosion;
import model.weapon.Spear;
import model.weapon.Sword;
import model.weapon.Weapon;
import utils.Action;
import utils.RandomCollection;

class Main {
    // "-" means the manual player. Each manual player has a different keymapping.
    // "-0" means the
    // keymapping of the zeroth manual player
    private static final java.util.Map<String, Controller> controllerMapping = ofEntries(
            entry("monkey", new RandomMoveController()), entry("simple", new SimpleController()),
            entry("-0", new KeyboardController(ofEntries(entry(KeyEvent.VK_UP, Action.UP),
                    entry(KeyEvent.VK_DOWN, Action.DOWN), entry(KeyEvent.VK_LEFT, Action.LEFT),
                    entry(KeyEvent.VK_RIGHT, Action.RIGHT),
                    entry(KeyEvent.VK_SPACE, Action.ATTACK)))),
            entry("-1", new KeyboardController(ofEntries(entry(KeyEvent.VK_W, Action.UP),
                    entry(KeyEvent.VK_S, Action.DOWN), entry(KeyEvent.VK_A, Action.LEFT),
                    entry(KeyEvent.VK_D, Action.RIGHT), entry(KeyEvent.VK_SHIFT, Action.ATTACK)))));
    private static final RandomCollection<Prop> props =
            new RandomCollection<Prop>().add(40, new SmallPointProp()).add(20, new BigPointProp())
                    .add(15, new SpeedUpProp()).add(15, new SlowDownProp()).add(10, new WineProp());
    private static final RandomCollection<Weapon> weapons =
            new RandomCollection<Weapon>().add(20, new BoxingGlove()).add(40, new Sword())
                    .add(40, new Spear()).add(5, new Explosion());

    public static void main(String[] args) {
        final int playerNums = args.length;
        if (playerNums <= 0) {
            System.err.println("The game needs at least one player to start.");
            System.exit(1);
        }
        System.out.printf("Hello FiOnaOpPai!\n");

        Map map;
        try {
            File mapFile = Path.of("maps/fiona.txt").toFile();
            map = Map.readMapFromFile(mapFile);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        List<String> playerNames = new ArrayList<>();
        List<Controller> controllers = new ArrayList<>();
        for (int i = 0, manualPlayerIndex = 0; i < playerNums; i++) {
            if (args[i].equals("-")) {
                if (controllerMapping.containsKey(args[i] + manualPlayerIndex))
                    controllers.add(controllerMapping.get(args[i] + manualPlayerIndex));
                else {
                    System.err.printf(
                            "KeyBoard controller for the player %d is not configured. Using simple controller instead.",
                            manualPlayerIndex);
                    controllers.add(controllerMapping.get("simple"));
                }
                manualPlayerIndex++;
                playerNames.add("Manual");
            } else {
                controllers.add(controllerMapping.get(args[i]));
                playerNames.add(args[i]);
            }
        }

        int renderRatio = map.getMaxWidth() / map.getWidth();
        View view = new View(map.getWidth() * renderRatio, map.getHeight() * renderRatio,
                renderRatio * 4);
        Game game = new Game(playerNames, playerNums, renderRatio, view, map, controllers, props,
                weapons);
        game.start();
    }
}
