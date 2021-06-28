package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.List;
import model.Pacman;
import model.interfaces.SwitchImageCallback;
import utils.Coordinate;
import static utils.ImageUtils.pacmanImgsFromFolder;

public class PacmanRenderer extends Renderer {
    private final Pacman pacman;
    private final List<BufferedImage> imgs;
    private int imgIdx = 0;

    public PacmanRenderer(Pacman pacman, int renderRatio) {
        super(renderRatio);
        this.pacman = pacman;
        String folderPath = String.format("assets/pacmans/pacman_%d", pacman.getID());
        this.imgs = pacmanImgsFromFolder(folderPath);
    }

    public void render(Graphics g) {
        if (this.pacman.needToRender()) {
            Coordinate realCoordinate = pacman.getRealCoordinate();
            int x = (int) (realCoordinate.getX().doubleValue() * this.renderRatio);
            int y = (int) (realCoordinate.getY().doubleValue() * this.renderRatio);
            BufferedImage image = imgs.get(imgIdx);

            rotateAndDraw(g, image, pacman.getFacing().getDegree(), x, y);
        }
        if (this.pacman.getSwitchImageCallbacks().size() == 0)
            imgIdx = (imgIdx + 1) % imgs.size();
        else {
            for (SwitchImageCallback switchImageCallback : this.pacman.getSwitchImageCallbacks())
                imgIdx = switchImageCallback.onSwitchImage(imgIdx) % imgs.size();
        }
    }

    private void rotateAndDraw(Graphics g, BufferedImage image, int degree, int x, int y) {
        AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(degree), image.getHeight(null) / 2,
                image.getWidth(null) / 2);
        AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
        ((Graphics2D) g).drawImage(op.filter(image, null), y, x, this.renderRatio, this.renderRatio, null);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}