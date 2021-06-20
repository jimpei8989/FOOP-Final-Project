package view;

import java.awt.*;

import model.Pickable;

public class ObjectRenderer implements Renderable {
    Pickable object;

    public ObjectRenderer(Pickable object) {
        this.object = object;
    }

    public void render(Graphics g) {
    }
}
