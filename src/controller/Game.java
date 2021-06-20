package controller;

import java.util.List;

import model.Tickable;
import model.World;

public class Game {
    private boolean running;
    private View view;
    private World world;

    public Game(World world) {
        this.world = world;
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
            Thread.sleep(ticks * 33); // 1/30 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
