package view;

import java.awt.*;

import model.Locatable;

public class ObjectRenderer implements Renderable {
    Locatable object;

    public ObjectRenderer(Locatable object) {
        this.object = object;
    }

    public void render(Graphics g) {
    }
}
