package Game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.KeyboardController;
import controller.RandomMoveController;
import model.Pacman;
import model.World;
import model.map.Map;
import model.prop.Prop;
import model.state.SpeedChange;
import model.weapon.Weapon;

import utils.Action;
import utils.RandomCollection;
import view.FooterPanel;
import view.TimePanel;
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
    private int countdown = 300;
    private Timer timer;

    public Game(int numPlayers, int renderRatio, View view, Map map, List<java.util.Map<Integer, Action>> keyControls,
            RandomCollection<Prop> props, RandomCollection<Weapon> weapons) {
        this.running = true;
        this.numPlayers = numPlayers;
        this.renderRatio = renderRatio;
        this.view = view;
        List<KeyboardController> keyboardControllers = new ArrayList<>();
        for (int i = 0; i < this.numPlayers; i++) {
            Pacman pacman = new Pacman("Fiona" + i, i, 300, 0, 1, map.getPacmanInitCoords().get(i));
            if (i != 0) {
                // slow down the computer for debugging
                pacman.addState(new SpeedChange(pacman, 30, 30 * 60));
                // pacman.addState(new SpeedChange(pacman, -4));
            }
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
            // leave one space for the timer
            this.addMapRenderer(new FooterPanel(pacman, map.getHeight() * renderRatio,
                    (map.getWidth() * renderRatio * (i + 1)) / (this.numPlayers + 1),
                    map.getWidth() * renderRatio / (this.numPlayers + 1), view.getFooterHeight()));
        }
        this.view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                for (KeyboardController keyboardController : keyboardControllers)
                    keyboardController.addKeyboardEvent(keyEvent);
            }
        });
        this.addMapRenderer(new MapRenderer(map, this.renderRatio));
        this.world = new World(this, map, this.pacmans, new ArrayList<>(), props, weapons);

        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;
                if (countdown < 0) {
                    running = false;
                }
            }
        });

        this.addMapRenderer(new TimePanel(this, map.getHeight() * renderRatio, 0,
                map.getWidth() * renderRatio / (this.numPlayers + 1), view.getFooterHeight()));
    }

    public int getCountdown() {
        return this.countdown;
    }

    public int getRenderRatio() {
        return this.renderRatio;
    }

    public void start() {
        new Thread(this::gameLoop).start();
    }

    private void gameLoop() {
        timer.start();
        while (this.running) {
            world.update();
            view.render();
            delay(1);
        }
        view.setSortedPacmans(this.pacmans);
        view.renderResult();
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
