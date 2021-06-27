package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import model.prop.Prop;
import utils.Coordinate;
import static utils.ImageUtils.propImgFromFile;

public class PropRenderer extends Renderer {
    private final BufferedImage propImg;
    private final Prop prop;

    public PropRenderer(Prop prop, int renderRatio) {
        super(renderRatio);
        this.prop = prop;
        this.propImg = propImgFromFile(prop.getName());
    }

    public void render(Graphics g) {
        Coordinate realCoordinate = this.prop.getRealCoordinate();
        int x = (int) (realCoordinate.getX().doubleValue() * this.renderRatio);
        int y = (int) (realCoordinate.getY().doubleValue() * this.renderRatio);
        g.drawImage(this.propImg, y, x, this.renderRatio, this.renderRatio, null);
    }

    @Override
    public boolean isActive() {
        return this.prop.isActive();
    }
}
