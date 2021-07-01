package model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import model.interfaces.Tickable;
import utils.Coordinate;
import utils.Direction;

public class Map implements Tickable {
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

        for (int i = 0; i < height; i++) {
            if (this.mapContent[i][0] instanceof Road != this.mapContent[i][width
                    - 1] instanceof Road) {
                throw new RuntimeException("Invalid map - wrong configuration");
            }
        }
        for (int j = 0; j < width; j++) {
            if ((this.mapContent[0][j] instanceof Road) != (this.mapContent[height
                    - 1][j] instanceof Road)) {
                throw new RuntimeException("Invalid map - wrong configuration");
            }
        }


        for (Coordinate[] highway : highways) {
            int ax = highway[0].getX().intValue();
            int ay = highway[0].getY().intValue();

            int bx = highway[1].getX().intValue();
            int by = highway[1].getY().intValue();

            if (this.mapContent[ax][ay] != null || this.mapContent[bx][by] != null) {
                throw new RuntimeException(String.format(
                        "Invalid map - wrong configuration (%d, %d) (%d, %d)", ax, ay, bx, by));
            }

            Highway a = new Highway(highway[0], highway[1]);
            Highway b = new Highway(highway[1], highway[0]);

            a.setPairing(b);
            b.setPairing(a);

            this.mapContent[ax][ay] = a;
            this.mapContent[bx][by] = b;
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
        return new Coordinate((coord.getX().intValue() % this.height + this.height) % this.height,
                (coord.getY().intValue() % this.width + this.width) % this.width);
    }

    private MapGrid getGrid(Coordinate coord) {
        coord = adjustCoordinate(coord);
        return this.mapContent[coord.getX().intValue()][coord.getY().intValue()];
    }

    public Coordinate nextCoordinate(Coordinate coord, Direction direction) {
        // Returns the next coordinate the pacman should go next if he's stepping on
        // *coord* and moving toward *direction*

        return adjustCoordinate(this.getGrid(coord).transferTo(direction));
    }

    public boolean canPass(Coordinate coord, Direction direction) {
        // Returns whether the move is valid if the pacman is stepping on *coord* and
        // moving toward *direction*

        return this.getGrid(this.nextCoordinate(coord, direction)).canPass(coord, direction);
    }

    public void onMoveEnd(Coordinate coord) {
        this.getGrid(coord).onMoveEnd();
    }

    public void onTurnBegin() {}

    public void onTurnEnd() {}

    public void onRoundBegin() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.mapContent[i][j].onRoundBegin();
            }
        }
    }

    public void onRoundEnd() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.mapContent[i][j].onRoundEnd();
            }
        }
    }
}
