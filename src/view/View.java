package view;

import java.awt.Graphics;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import utils.Active;

public class View extends JFrame {
    private int width = 1440, height = 300;
    private List<Renderable> pacmanRenderers = new LinkedList<>(), mapRenderers = new LinkedList<>(),
            objectRenderers = new LinkedList<>();
    private Canvas canvas = new Canvas();

    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(this.canvas);
        this.setSize(this.width, this.height);
        this.setContentPane(this.canvas);
        this.setVisible(true);
    }

    public View() {

    }

    public View(int width, int height, int footerHeight) {
        this.width = width;
        this.height = height;
        this.setSize(this.width + 14, this.height + 37 + footerHeight); // magic, don't change it
        this.setContentPane(this.canvas);
        this.setVisible(true);
    }

    public View(int width, int height, int footerHeight, List<Renderable> pacmanRenderers, List<Renderable> mapRenderers,
            List<Renderable> objectRenderers) {
        this.width = width;
        this.height = height;
        this.pacmanRenderers = pacmanRenderers;
        this.mapRenderers = mapRenderers;
        this.objectRenderers = objectRenderers;
        this.setSize(this.width + 14, this.height + 37 + footerHeight); // magic, don't change it
        this.setContentPane(this.canvas);
        this.setVisible(true);
    }

    public void addPacmanRenderer(Renderable pacmanRenderer) {
        this.pacmanRenderers.add(pacmanRenderer);
    }

    public void addMapRenderer(Renderable mapRenderer) {
        this.mapRenderers.add(mapRenderer);
    }

    public void addObjectRenderer(Renderable objectRenderer) {
        this.objectRenderers.add(objectRenderer);
    }

    public void render() {
        // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        this.canvas.repaint();
    }

    private void render(Graphics g) {
        List<List<Renderable>> renderersArray = new ArrayList<>(
                Arrays.asList(this.pacmanRenderers, this.mapRenderers, this.objectRenderers));
        for (List<Renderable> renderers : renderersArray) {
            ListIterator<Renderable> iter = renderers.listIterator();
            while (iter.hasNext()) {
                Renderable renderer = iter.next();
                if (!((Active) renderer).isActive())
                    iter.remove();
                else
                    renderer.render(g);
            }
        }
    }

    private class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g /* paintbrush */) {
            super.paintComponent(g);
            // Now, let's paint
            g.setColor(Color.BLACK); // paint background with all white
            g.fillRect(0, 0, View.this.width, View.this.height);

            View.this.render(g);
        }
    }
}
