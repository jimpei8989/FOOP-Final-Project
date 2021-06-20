package view;

import java.awt.*;

import utils.Active;

public interface Renderable extends Active{
    void render(Graphics g);
}
