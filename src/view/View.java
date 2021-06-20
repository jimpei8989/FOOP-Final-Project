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

import utils.Active;
import utils.Renderable;

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

    public View(List<Renderable> pacmanRenderers, List<Renderable> mapRenderers, List<Renderable> objectRenderers) {
        this.pacmanRenderers = pacmanRenderers;
        this.mapRenderers = mapRenderers;
        this.objectRenderers = objectRenderers;
    }

    public void add_pacman(Renderable pacmanRenderer) {
        this.pacmanRenderers.add(pacmanRenderer);
    }

    public void add_map(Renderable mapRenderer) {
        this.mapRenderers.add(mapRenderer);
    }

    public void add_object(Renderable objectRenderer) {
        this.objectRenderers.add(objectRenderer);
    }

    public void render(Graphics g) {
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
    
    public class Canvas extends JPanel {

        public void render() {
            this.repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

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
