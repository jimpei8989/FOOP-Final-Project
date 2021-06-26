package view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import model.weapon.Weapon;
import utils.Coordinate;

import static utils.ImageUtils.weaponImgFromFile;

public class WeaponRenderer extends ObjectRenderer {
    private final Weapon weapon;

    private final BufferedImage weaponImg;

    public WeaponRenderer(Weapon weapon) {
        super();
        this.weapon = weapon;
        this.weaponImg = weaponImgFromFile(weapon.getName());
    }

    public void render(Graphics g) {
        Coordinate realCoordinate = weapon.getRealCoordinate();
        int x = (int) (realCoordinate.getX().doubleValue() * 10);
        int y = (int) (realCoordinate.getY().doubleValue() * 10);

        rotateAndDraw(g, this.weaponImg, (int) weapon.getDegree(), x, y);
    }

    private void rotateAndDraw(Graphics g, BufferedImage image, int degree, int x, int y) {
        AffineTransform trans = AffineTransform.getRotateInstance(Math.toRadians(degree), image.getHeight(null) / 2,
                image.getWidth(null) / 2);
        AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
        ((Graphics2D) g).drawImage(op.filter(image, null), y, x, 10, 10, null);
    }

    @Override
    public boolean isActive() {
        return weapon.needToRender();
    }
}
