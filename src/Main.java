import view.View;

import java.util.ArrayList;

import Game.Game;
import model.World;

class Main {
    public static void main(String[] args) {
        System.out.printf("Hello FiOnaOpPai!\n");
        View view = new View(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Game game = new Game(view);
        game.start();
    }
}