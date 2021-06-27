package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import model.Pacman;
import static utils.ImageUtils.pacmanImgsFromFolder;

public class FooterPanel implements Renderable {
    private int x, y, width, height;
    private Pacman pacman;
    private final BufferedImage img;
    private Font font = new Font("Calibri", Font.PLAIN, 20);

    public FooterPanel(Pacman pacman, int x, int y, int width, int height) {
        this.pacman = pacman;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        String folderPath = String.format("assets/pacmans/pacman_%d", pacman.getID());
        this.img = pacmanImgsFromFolder(folderPath).get(2);
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.y, this.x, this.width, this.height);
        g.setColor(Color.WHITE);
        g.drawRect(this.y, this.x, this.width, this.height);
        g.drawImage(this.img, this.y + 2, this.x + 2, 30, 30, null);

        g.setColor(Color.WHITE);
        g.setFont(this.font);
        g.drawString(this.pacman.getName(), this.y + 35, this.x + 24);
        g.drawString(String.format("move: %02d/%d", this.pacman.getMoveCd().getCurrent(), 
                this.pacman.getMoveCd().getInterval()), this.y + 35, this.x + 44);
        g.drawString(String.format("%d", this.pacman.getScore()), this.y + 35, this.x + 64);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
