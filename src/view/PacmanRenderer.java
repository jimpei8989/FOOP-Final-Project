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
        g.fillOval((realCoordinate.getX() * 10).intValue(), (realCoordinate.getY() * 10).intValue(), 10, 10);
    }
}