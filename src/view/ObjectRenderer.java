package view;

import java.awt.*;

import model.interfaces.Locatable;

public class ObjectRenderer extends Renderer {
    Locatable object;

    public ObjectRenderer(Locatable object, int renderRatio) {
        super(renderRatio);
        this.object = object;
    }

    public void render(Graphics g) {
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
