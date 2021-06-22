import view.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import Game.Game;
import model.map.Map;

class Main {
    public static void main(String[] args) {
        System.out.printf("Hello FiOnaOpPai!\n");

        Map map;
        try {
            File mapFile = Path.of("maps/fiona.txt").toFile();
            map = Map.readMapFromFile(mapFile);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        View view = new View(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Game game = new Game(4, view, map);
        game.start();
    }
}