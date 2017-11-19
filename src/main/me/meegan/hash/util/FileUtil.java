package me.meegan.hash.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class for file associated functionality
 */
public class FileUtil {
    /**
     * Reads the contents of the specified file, and returns a byte array of the content.
     *
     * @param path absolute/relative path to file
     * @return byte array of file content.
     * @throws IOException if file cannot be found/read
     */
    public static byte[] readFileContent(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
