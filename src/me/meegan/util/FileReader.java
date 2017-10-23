package me.meegan.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Simple example showing how to read file contents as raw bytes, and how to output hex type strings, and how to create a simple hash based on the contents.
 *
 * @author mdixon
 *
 */
public class FileReader {
    /**
     * Reads the contents of the specified file, and returns a byte array of the content.
     *
     * @param pathName
     * @return byte array of file content.
     * @throws IOException
     */
    public static byte[] readFileContent(String pathName) throws IOException {

        return Files.readAllBytes(Paths.get(pathName));
    }
}