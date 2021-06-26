package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import model.map.Map;
import model.map.MapGrid;
import model.map.Wall;

import static utils.ImageUtils.wallImgFromFile;

public class MapRenderer extends Renderer {
    private final Map map;
    private final BufferedImage wallImg;

    public MapRenderer(Map map, int renderRatio) {
        super(renderRatio);
        this.map = map;
        this.wallImg = wallImgFromFile();
    }

    public void render(Graphics g) {
        int height = map.getHeight(), width = map.getWidth();
        MapGrid[][] mapContent = map.getMapContent();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mapContent[i][j] instanceof Wall) {
                    g.drawImage(wallImg, j * this.renderRatio, i * this.renderRatio, this.renderRatio, this.renderRatio, null);
                }
            }
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
