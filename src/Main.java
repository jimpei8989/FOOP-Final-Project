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

        int renderRatio = map.getMaxWidth() / map.getWidth();
        System.out.printf(
                "mapWidth: %d, mapHeight: %d, mapMaxWidth: %d, renderRatio: %d width: %d, height: %d, renderRatio: %d\n",
                map.getWidth(), map.getHeight(), map.getMaxWidth(), renderRatio, map.getWidth() * renderRatio,
                map.getHeight() * renderRatio, renderRatio);

        View view = new View(map.getWidth() * renderRatio, map.getHeight() * renderRatio);
        Game game = new Game(4, renderRatio, view, map);
        game.start();
    }
}