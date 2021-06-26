package model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import utils.Coordinate;
import utils.Direction;

public class Map {
    private final int height;
    private final int width, maxWidth = 1440;
    private final MapGrid[][] mapContent;
    private final List<Coordinate> pacmanInitCoords;
    private final List<Coordinate> roadCoords;

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

        // Read initial location of P pacmans
        int p = reader.nextInt();
        reader.nextLine();
        List<Coordinate> pacmanInitCoords = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            pacmanInitCoords.add(new Coordinate(reader.nextInt(), reader.nextInt()));
            reader.nextLine();
        }

        return new Map(n, m, mapContent, highways, pacmanInitCoords);
    }

    public Map(int height, int width, String[] mapContent, List<Coordinate[]> highways,
            List<Coordinate> pacmanInitCoords) {
        this.height = height;
        this.width = width;

        this.mapContent = new MapGrid[height][width];
        this.pacmanInitCoords = pacmanInitCoords;
        this.roadCoords = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mapContent[i].charAt(j) == '.') {
                    this.mapContent[i][j] = new Road(new Coordinate(i, j));
                    this.roadCoords.add(new Coordinate(i, j));
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

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public MapGrid[][] getMapContent() {
        return this.mapContent;
    }

    public List<Coordinate> getPacmanInitCoords() {
        return this.pacmanInitCoords;
    }

    public List<Coordinate> getRoadCoords() {
        return this.roadCoords;
    }

    public Coordinate adjustCoordinate(Coordinate coord) {
        return new Coordinate(coord.getX().intValue() % this.height, coord.getY().intValue() % this.width);
    }

    private MapGrid getGrid(Coordinate coord) {
        coord = adjustCoordinate(coord);
        return this.mapContent[coord.getX().intValue()][coord.getY().intValue()];
    }

    public Coordinate nextCoordinate(Coordinate coord, Direction direction) {
        // Returns the next coordinate the pacman should go next if he's stepping on
        // *coord* and moving toward *direction*

        // TODO: Should we also consider the Pacman?
        return adjustCoordinate(this.getGrid(coord).transferTo(direction));
    }

    public boolean canPass(Coordinate coord, Direction direction) {
        // Returns whether the move is valid if the pacman is stepping on *coord* and
        // moving toward *direction*

        // TODO: Should we also consider the Pacman?
        return this.getGrid(this.nextCoordinate(coord, direction)).canPass(coord, direction);
    }
}
