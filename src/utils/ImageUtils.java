package utils;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class ImageUtils {
    public static final String SUPPORTED_FILE_NAME_PATTERN = "([1-9][0-9]*|0)\\.(bmp|jpg|png)";

    public static List<BufferedImage> pacmanImgsFromFolder(String folderPath) {
        try {
            List<File> files = Files.list(Path.of(folderPath)).map(ImageUtils::readFile)
                    .filter(ImageUtils::validateFileName).collect(toList());
            sortFilesByAscendingIndex(files);
            return files.stream().map(ImageUtils::readImage).collect(toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static BufferedImage wallImgFromFile() {
        try {
            String filePath = "assets/wall/0.png";
            return readImage(readFile(Path.of(filePath)));
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static BufferedImage weaponImgFromFile(String weaponName) {
        try {
            String filePath = String.format("assets/weapons/%.png", weaponName);
            return readImage(readFile(Path.of(filePath)));
        } catch (RuntimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static File readFile(Path filePath) {
        return filePath.toFile();
    }

    private static BufferedImage readImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validateFileName(File file) {
        String fileName = file.getName();
        return Pattern.matches(SUPPORTED_FILE_NAME_PATTERN, fileName);
    }

    private static void sortFilesByAscendingIndex(List<File> imgFiles) {
        imgFiles.sort((f1, f2) -> {
            int f1Index = Integer.parseInt(f1.getName().split("\\.")[0]);
            int f2Index = Integer.parseInt(f2.getName().split("\\.")[0]);
            return f1Index - f2Index;
        });
    }
}
