package view;

import java.awt.*;
import Game.Game;

public class TimePanel implements Renderable {
    public int x, y, width, height;
    public Game game;
    private Font font = new Font("Calibri", Font.PLAIN, 20);

    public TimePanel(Game game, int x, int y, int width, int height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(this.y, this.x, this.width, this.height);
        g.setColor(Color.WHITE);
        g.drawRect(this.y, this.x, this.width, this.height);
        g.setColor(Color.WHITE);
        g.setFont(this.font);
        g.drawString(
                String.format("%02d:%02d", this.game.getCountdown() / 60, Math.max(this.game.getCountdown() % 60, 0)),
                this.y + 35, this.x + 35);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
