package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Pacman;
import utils.Active;

import static utils.ImageUtils.pacmanImgsFromFolder;
import static utils.ImageUtils.trophyImgFromFile;

public class View extends JFrame {
    private int width = 1440, height = 300, footerHeight = 30;
    private List<Renderable> pacmanRenderers = new LinkedList<>(),
            mapRenderers = new LinkedList<>(), objectRenderers = new LinkedList<>();
    private Canvas canvas = new Canvas();
    private ResultCanvas resultCanvas = new ResultCanvas();
    private Font font = new Font("Calibri", Font.PLAIN, 20);
    private List<Pacman> pacmans;

    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(this.canvas);
        this.setSize(this.width, this.height);
        this.setContentPane(this.canvas);
        this.setVisible(true);
    }

    public View() {}

    public View(int width, int height, int footerHeight) {
        this.width = width;
        this.height = height;
        this.footerHeight = footerHeight;
        this.setSize(this.width + 14, this.height + 37 + footerHeight); // magic, don't change it
        this.setContentPane(this.canvas);
        this.setVisible(true);
    }


    public int getFooterHeight() {
        return this.footerHeight;
    }

    public void setSortedPacmans(List<Pacman> pacmans) {
        this.pacmans = pacmans;
        this.pacmans.sort(new PacmanComparator());
    }

    public void addPacmanRenderer(Renderable pacmanRenderer) {
        this.pacmanRenderers.add(pacmanRenderer);
    }

    public void addMapRenderer(Renderable mapRenderer) {
        this.mapRenderers.add(mapRenderer);
    }

    public void addObjectRenderer(Renderable objectRenderer) {
        synchronized (this.objectRenderers) {
            this.objectRenderers.add(objectRenderer);
        }
    }

    public void render() {
        // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        this.canvas.repaint();
    }

    public void renderResult() {
        this.setContentPane(this.resultCanvas);
        this.setVisible(true);
        this.resultCanvas.repaint();
    }

    private void render(Graphics g) {
        synchronized (objectRenderers) {
            List<List<Renderable>> renderersArray = new ArrayList<>(
                    Arrays.asList(this.pacmanRenderers, this.mapRenderers, this.objectRenderers));
            for (List<Renderable> renderers : renderersArray) {
                ListIterator<Renderable> iter = renderers.listIterator();
                while (iter.hasNext()) {
                    Renderable renderer = iter.next();
                    if (!((Active) renderer).isActive()) {
                        iter.remove();
                    } else
                        renderer.render(g);
                }
            }
        }
    }

    private void renderResult(Graphics g) {
        g.setFont(font);
        for (int i = 0; i < this.pacmans.size(); i++) {
            Pacman pacman = this.pacmans.get(i);
            renderPacmanRanking(g, i, pacman, 50 * (i + 1), 560);
        }
    }

    private void renderPacmanRanking(Graphics g, int ranking, Pacman pacman, int x, int y) {
        String folderPath = String.format("assets/pacmans/pacman_%d", pacman.getID());

        if (ranking < 3) {
            BufferedImage trophy = trophyImgFromFile(ranking);
            g.drawImage(trophy, y + 2, x + 2, 30, 30, null);

        }
        BufferedImage img = pacmanImgsFromFolder(folderPath).get(2);
        g.drawImage(img, y + 40, x + 2, 30, 30, null);
        g.setColor(Color.WHITE);
        g.drawString(pacman.getName(), y + 80, x + 24);
        g.drawString(String.format("%d", pacman.getScore()), y + 200, x + 24);
    }

    private class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g /* paintbrush */) {
            super.paintComponent(g);
            // Now, let's paint
            g.setColor(Color.BLACK); // paint background with all white
            g.fillRect(0, 0, View.this.getWidth(), View.this.getHeight());

            View.this.render(g);
        }
    }

    private class ResultCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, View.this.getWidth(), View.this.getHeight());

            View.this.renderResult(g);
        }
    }
}


class PacmanComparator implements Comparator<Pacman> {
    @Override
    public int compare(Pacman p1, Pacman p2) {
        return p2.getScore() - p1.getScore();
    }
}
