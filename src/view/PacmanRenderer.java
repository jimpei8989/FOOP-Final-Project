package view;

import java.awt.*;
import java.util.List;
import model.Pacman;
import utils.Coordinate;
import static utils.ImageUtils.pacmanImgsFromFolder;

public class PacmanRenderer implements Renderable {
    private final Pacman pacman;
    private final List<Image> imgs;
    private int imgIdx = 0;

    public PacmanRenderer(Pacman pacman) {
        this.pacman = pacman;
        String folderPath = String.format("assets/pacman_%d", pacman.getID());
        this.imgs = pacmanImgsFromFolder(folderPath);
    }

    public void render(Graphics g) {
        Coordinate realCoordinate = pacman.getRealCoordinate();
        // g.setColor(Color.RED);
        // g.fillOval((int) (realCoordinate.getX().doubleValue() * 10), (int)
        // (realCoordinate.getY().doubleValue() * 10),
        // 10, 10);
        g.drawImage(imgs.get(imgIdx), (int) (realCoordinate.getX().doubleValue() * 10),
                (int) (realCoordinate.getY().doubleValue() * 10), 10, 10, null);
        imgIdx = (imgIdx + 1) % imgs.size();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}