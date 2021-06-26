package Game;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controller.KeyboardController;
import controller.RandomMoveController;
import model.interfaces.Tickable;
import model.Pacman;
import model.World;
import model.map.Map;
import utils.Action;
import utils.Coordinate;
import view.FooterPanel;
import view.MapRenderer;
import view.PacmanRenderer;
import view.Renderable;
import view.View;

public class Game {
    private boolean running;
    private int numPlayers, renderRatio;
    private View view;
    private World world;
    private List<Pacman> pacmans = new ArrayList<>();

    public Game(int numPlayers, int renderRatio, View view, Map map, List<java.util.Map<Integer, Action>> keyControls) {
        this.numPlayers = numPlayers;
        this.renderRatio = renderRatio;
        this.view = view;
        List<KeyboardController> keyboardControllers = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            Pacman pacman = new Pacman("Fiona" + i, i, 300, 300, 1, map.getPacmanInitCoords().get(i));
            if (keyControls.get(i) != null) {
                KeyboardController controller = new KeyboardController(keyControls.get(i));
                keyboardControllers.add(controller);
                pacman.addController(controller);
            } else {
                RandomMoveController controller = new RandomMoveController();
                pacman.addController(controller);
            }
            this.pacmans.add(pacman);
            this.addPacmanRenderer(new PacmanRenderer(pacman, this.renderRatio));
            this.addMapRenderer(
                    new FooterPanel(pacman, map.getHeight() * renderRatio, map.getWidth() * renderRatio * i / this.numPlayers,
                            map.getWidth() * renderRatio / this.numPlayers, view.getFooterHeight()));
        }
        this.view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                for (KeyboardController keyboardController : keyboardControllers)
                    keyboardController.addKeyboardEvent(keyEvent);
            }
        });
        // addMapRenderer(new MapRenderer(map, this.renderRatio));
        this.world = new World(this, map, this.pacmans, new ArrayList<>());
    }

    public void start() {
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        running = true;
        while (running) {
            world.update();
            view.render();
            delay(1);
        }
    }

    private void delay(int ticks) {
        try {
            Thread.sleep(ticks * 16); // 1/60 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addMapRenderer(Renderable mapRenders) {
        view.addMapRenderer(mapRenders);
    }

    public void addPacmanRenderer(Renderable pacmanRenders) {
        view.addPacmanRenderer(pacmanRenders);
    }

    public void addObjectRenderer(Renderable objectRenders) {
        view.addObjectRenderer(objectRenders);
    }
}
