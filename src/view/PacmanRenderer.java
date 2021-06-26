package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;
import model.Pacman;
import utils.Coordinate;
import static utils.ImageUtils.pacmanImgsFromFolder;

public class PacmanRenderer implements Renderable {
    private final Pacman pacman;
    private final List<BufferedImage> imgs;
    private int imgIdx = 0;

    public PacmanRenderer(Pacman pacman) {
        this.pacman = pacman;
        String folderPath = String.format("assets/pacmans/pacman_%d", pacman.getID());
        this.imgs = pacmanImgsFromFolder(folderPath);
    }

    public void render(Graphics g) {
        Coordinate realCoordinate = pacman.getRealCoordinate();
        int x = (int) (realCoordinate.getX().doubleValue() * 10);
        int y = (int) (realCoordinate.getY().doubleValue() * 10);
        BufferedImage image = imgs.get(imgIdx);

        rotateAndDraw(g, image, pacman.getFacing().getDegree(), x, y);
        imgIdx = (imgIdx + 1) % imgs.size();
    }

    private void rotateAndDraw(Graphics g, BufferedImage image, int degree, int x, int y) {
        AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(degree), image.getHeight(null) / 2,
                image.getWidth(null) / 2);
        AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
        ((Graphics2D) g).drawImage(op.filter(image, null), y, x, 10, 10, null);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}