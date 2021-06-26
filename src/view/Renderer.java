package view;

import java.awt.Graphics;

public abstract class Renderer implements Renderable {
    protected int renderRatio;

    public Renderer(int renderRatio) {
        this.renderRatio = renderRatio;
    }

    @Override
    public abstract void render(Graphics g);
}
