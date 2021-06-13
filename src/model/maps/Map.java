package model.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import utils.Coordinate;

public class Map {
    private final int height;
    private final int width;
    private final MapGrid[][] mapContent;

    // TODO
    public static Map readMapFile(File f) {
        try {
            Scanner reader = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println("! Map file not found...");
            System.exit(1);
        }
        // Read height, width

        // Read road (.) / wall (#) (a big char matrix) / others (?)

        // Read M highways
        return new Map(0, 0, null, null);
    }

    public Map(int height, int width, String[][] mapContent, List<Coordinate[]> highways) {
        this.height = height;
        this.width = width;

        this.mapContent = new MapGrid[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mapContent[i][j] == ".") {
                    this.mapContent[i][j] = new Road(new Coordinate(i, j));
                } else if (mapContent[i][j] == "#") {
                    this.mapContent[i][j] = new Wall(new Coordinate(i, j));
                }
            }
        }

        for (Coordinate[] highway : highways) {
            int ax = highway[0].getX().intValue();
            int ay = highway[0].getY().intValue();

            int bx = highway[1].getX().intValue();
            int by = highway[1].getY().intValue();

            this.mapContent[ax][ay] = new Highway(highway[0], highway[1]);
            this.mapContent[bx][by] = new Highway(highway[1], highway[0]);
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Coordinate adjustCoordinate(Coordinate coord) {
        return new Coordinate(coord.getX().intValue() % this.height, coord.getY().intValue() % this.width);
    }
}
