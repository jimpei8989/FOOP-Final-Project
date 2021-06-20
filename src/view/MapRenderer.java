package view;

import java.awt.*;

import model.map.Map;

public class MapRenderer implements Renderable {
    private Map map;

    public MapRenderer(Map map) {
        this.map = map;
    }

    public void render(Graphics g) {

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
