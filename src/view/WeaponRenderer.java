package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import model.weapon.Weapon;
import utils.Coordinate;

import static utils.ImageUtils.weaponImgFromFile;

public class WeaponRenderer extends Renderer {
    private final BufferedImage weaponImg;
    private final Weapon weapon;

    public WeaponRenderer(Weapon weapon, int renderRatio) {
        super(renderRatio);
        this.weapon = weapon;
        this.weaponImg = weaponImgFromFile(weapon.getName());
    }

    public void render(Graphics g) {
        if (this.weapon.needToRender()) {
            Coordinate realCoordinate = this.weapon.getRealCoordinate();
            int x = (int) (realCoordinate.getX().doubleValue() * this.renderRatio);
            int y = (int) (realCoordinate.getY().doubleValue() * this.renderRatio);

            rotateAndDraw(g, this.weaponImg, (int) this.weapon.getDegree(), x, y);
        }
    }

    private void rotateAndDraw(Graphics g, BufferedImage image, int degree, int x, int y) {
        double zoom = this.weapon.getZoom();
        int newWidth = (int) (this.renderRatio * zoom);
        int newHeight = (int) (this.renderRatio * zoom);
        int deltaWidth = (this.renderRatio - newWidth) / 2;
        int deltaHeight = (this.renderRatio - newHeight) / 2;
        if (degree == 0) {
            g.drawImage(this.weaponImg, y + deltaWidth, x + deltaHeight, newWidth, newHeight, null);
        } else {
            AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(degree), image.getHeight(null) / 2,
                    image.getWidth(null) / 2);
            AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);

            ((Graphics2D) g).drawImage(op.filter(image, null), y + deltaWidth, x + deltaHeight, newWidth, newHeight,
                    null);
        }
    }

    @Override
    public boolean isActive() {
        return this.weapon.isActive();
    }
}
