package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import model.prop.Prop;
import utils.Coordinate;
import static utils.ImageUtils.propImgFromFile;

public class PropRenderer extends ObjectRenderer {
    private final BufferedImage propImg;

    public PropRenderer(Prop prop, int renderRatio) {
        super(prop, renderRatio);
        this.propImg = propImgFromFile(prop.getName());
    }

    public void render(Graphics g) {
        Coordinate realCoordinate = object.getRealCoordinate();
        int x = (int) (realCoordinate.getX().doubleValue() * this.renderRatio);
        int y = (int) (realCoordinate.getY().doubleValue() * this.renderRatio);
        g.drawImage(this.propImg, y, x, this.renderRatio, this.renderRatio, null);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
