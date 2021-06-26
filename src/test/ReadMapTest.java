package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Test;

import model.map.Map;

public class ReadMapTest {
    private String getFiona() {
        return "11 70\n######################################################################\n#....................................................................#\n#...#####.......#####..............#####........#####................#\n#...#.......#...#...#..............#...#........#...#.........#......#\n#...#####.......#...#.####..####...#...#..#####.#####.####...........#\n#...#.......#...#...#.#...#.#..##..#...#..#...#.#.....#..##...#......#\n#...#.......#...#####.#...#.###.#..#####..#####.#.....###.#...#......#\n#.........................................#..........................#\n#.........................................#..........................#\n#....................................................................#\n######################################################################\n3\n4 18 4 37\n5 44 3 50\n5 29 5 55\n";
    }

    @Test
    public void testReadMapSuccess() throws Exception {
        Scanner scanner = new Scanner(getFiona());
        try {
            Map map = Map.readMap(scanner);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void testReadMapFile() throws FileNotFoundException {
        File mapFile = Path.of("maps/fiona.txt").toFile();
        Map map = Map.readMapFromFile(mapFile);
    }
}
