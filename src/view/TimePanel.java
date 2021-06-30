package view;

import java.awt.*;
import Game.Game;
import model.utils.CoolDown;

public class TimePanel implements Renderable {
    public int x, y, width, height;
    public Game game;
    private Font font = new Font("Calibri", Font.BOLD, 40);
    private CoolDown fontColorSwitch = new CoolDown(1, 20);
    private static Color fontColor = Color.BLACK, myRed = new Color(215, 17, 27);

    public TimePanel(Game game, int x, int y, int width, int height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(this.y, this.x, this.width, this.height);
        g.setColor(new Color(200, 200, 200));
        g.fillRoundRect(this.y + this.width / 5, this.x + this.height / 5, this.width * 3 / 5,
                this.height * 3 / 5, 10, 10);

        if (this.game.getCountdown() <= 0)
            fontColor = myRed;
        else if (this.game.getCountdown() <= 10) {
            this.fontColorSwitch.update();
            if (this.fontColorSwitch.available()) {
                fontColor = fontColor == Color.BLACK ? myRed : Color.BLACK;
                this.fontColorSwitch.reset();
            }
        }

        g.setColor(fontColor);
        g.setFont(this.font);
        String time = String.format("%02d:%02d", this.game.getCountdown() / 60,
                Math.max(this.game.getCountdown() % 60, 0));
        int stringWidth = g.getFontMetrics().stringWidth(time),
                stringHeight = g.getFontMetrics().getHeight();

        g.drawString(time, this.y + this.width / 2 - stringWidth / 2,
                this.x + this.height / 2 + stringHeight / 4);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
