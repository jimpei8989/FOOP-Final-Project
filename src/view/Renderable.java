package view;

import java.awt.Graphics;

import utils.Active;

public interface Renderable extends Active{
    void render(Graphics g);
}
