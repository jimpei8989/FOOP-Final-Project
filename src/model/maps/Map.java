package model.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import utils.Coordinate;

public class Map {
    private final int height;
    private final int width;
    private final MapGrid[][] mapContent;

    public static Map readMapFromFile(File f) throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(f);
            Map map = readMap(reader);
            reader.close();
            return map;
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public static Map readMap(Scanner reader) throws IllegalArgumentException {
        int n = reader.nextInt(), m = reader.nextInt();
        reader.nextLine();

        // Read road (.) / wall (#) (a big char matrix) / others (?)
        String[] mapContent = new String[n];
        for (int i = 0; i < n; i++) {
            mapContent[i] = reader.nextLine();
            if (mapContent[i].length() != m) {
                throw new IllegalArgumentException("Wrong line width");
            }
        }

        // Read K highways
        int k = reader.nextInt();
        reader.nextLine();
        List<Coordinate[]> highways = new ArrayList<Coordinate[]>();
        for (int i = 0; i < k; i++) {
            Coordinate[] tmp = new Coordinate[2];
            tmp[0] = new Coordinate(reader.nextInt(), reader.nextInt());
            tmp[1] = new Coordinate(reader.nextInt(), reader.nextInt());
            reader.nextLine();
            highways.add(tmp);
        }
        return new Map(n, m, mapContent, highways);
    }

    public Map(int height, int width, String[] mapContent, List<Coordinate[]> highways) {
        this.height = height;
        this.width = width;

        this.mapContent = new MapGrid[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mapContent[i].charAt(j) == '.') {
                    this.mapContent[i][j] = new Road(new Coordinate(i, j));
                } else if (mapContent[i].charAt(j) == '#') {
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
