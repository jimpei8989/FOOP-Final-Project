package view;

import java.awt.*;

import model.Pacman;
import utils.Coordinate;

public class PacmanRenderer implements Renderable {
    private Pacman pacman;

    public PacmanRenderer(Pacman pacman) {
        this.pacman = pacman;
    }

    public void render(Graphics g) {
        Coordinate realCoordinate = pacman.getRealCoordinate();
        g.fillOval((int)(realCoordinate.getX().doubleValue() * 10), (int)(realCoordinate.getY().doubleValue() * 10), 10, 10);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}