package view;

import java.awt.Graphics;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Pacman;
import utils.Active;
import utils.Coordinate;

public class View extends JFrame {
    public static final int WIDTH = 800, HEIGHT = 300;
    private List<Renderable> pacmanRenderers = new LinkedList<>(), mapRenderers = new LinkedList<>(),
            objectRenderers = new LinkedList<>();
    private final Canvas canvas = new Canvas();

    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(this.canvas);
        this.setSize(View.WIDTH, View.HEIGHT);
        this.setContentPane(this.canvas);
        this.setVisible(true);
    }

    public View() {

    }

    public View(List<Renderable> pacmanRenderers, List<Renderable> mapRenderers, List<Renderable> objectRenderers) {
        this.pacmanRenderers = pacmanRenderers;
        this.mapRenderers = mapRenderers;
        this.objectRenderers = objectRenderers;
        Coordinate coordinate = new Coordinate(10, 20);
        System.out.println("Hello");
        addPacmanRender(new PacmanRenderer(new Pacman("Dylan", 0, 300, 300, 2, coordinate)));
    }

    public void addPacmanRender(Renderable pacmanRenderer) {
        this.pacmanRenderers.add(pacmanRenderer);
    }

    public void addMapRender(Renderable mapRenderer) {
        this.mapRenderers.add(mapRenderer);
    }

    public void addObjectRender(Renderable objectRenderer) {
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
            g.setColor(Color.WHITE); // paint background with all white
            g.fillRect(0, 0, View.WIDTH, View.HEIGHT);

            View.this.render(g);
        }
    }
}
