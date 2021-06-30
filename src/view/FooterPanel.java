package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import model.Pacman;
import model.state.State;

import static utils.ImageUtils.pacmanImgsFromFolder;
import static utils.ImageUtils.weaponImgFromFile;
import static utils.ImageUtils.statesImgFromFile;

public class FooterPanel implements Renderable {
    private int x, y, width, height;
    private Pacman pacman;
    private final BufferedImage img;
    private Font font = new Font("Calibri", Font.PLAIN, 20);
    private HashMap<String, BufferedImage> stateImageMap = new HashMap<>();

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
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(Color.WHITE);
        g.drawRect(this.y, this.x, this.width, this.height);
        g.drawImage(this.img, this.y + 2, this.x + 2, 30, 30, null);
        ((Graphics2D) g).setStroke(new BasicStroke(1));

        g.setColor(Color.WHITE);
        g.setFont(this.font);
        g.drawString(this.pacman.getName(), this.y + 35, this.x + 24);

        g.setColor(Color.RED);
        g.fillRect(this.y + 2, this.x + this.height - 5, this.width - 3, 5);
        g.setColor(Color.GREEN);
        g.fillRect(this.y + 2, this.x + this.height - 5,
                (int) ((this.width - 3) * this.pacman.getHP() / (double) this.pacman.getFullHP()),
                5);

        if (this.pacman.getWeapon() != null) {
            BufferedImage weaponImg = weaponImgFromFile(this.pacman.getWeapon().getName());
            g.drawImage(weaponImg, this.y + 105, this.x + 10, 15, 15, null);
        }

        ArrayList<State> states = new ArrayList<>(this.pacman.getStates().values());
        for (int i = 0, RenderableIndex = 0; i < states.size(); i++) {
            State state = states.get(i);
            try {
                if (!this.stateImageMap.containsKey(state.getName()))
                    this.stateImageMap.put(state.getName(), statesImgFromFile(state.getName()));
                g.drawImage(this.stateImageMap.get(state.getName()),
                        this.y + 125 + RenderableIndex * 15, this.x + 10, 15, 15, null);
                RenderableIndex++;
            } catch (IllegalArgumentException e) {

            }
        }

        g.setColor(Color.WHITE);
        g.drawString(String.format("Score: %d", this.pacman.getScore()), this.y + 35, this.x + 44);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
