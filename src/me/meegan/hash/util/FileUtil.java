package me.meegan.hash.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class FileUtil {
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

    public static void list(String dirPath) {

        // Would normally use a specific path, for now just use current directory.
        File path = new File(dirPath);

        // Get a list of all files and directories.
        File[] files = path.listFiles();

        if (files != null) {

            // iterate over files and directories
            for (File next : files) {

                // Test if file (or directory)
                boolean isFile = next.isFile();

                // Get name and last modified information.
                String name = next.getName();

                long lastModified = next.lastModified();

                System.out.print("Name: " + name + ", modified: " + new Date(lastModified));

                if ( isFile ) {
                    // for files, the length can also be read.
                    long fileLength = next.length();

                    System.out.print(", Length: " + fileLength);
                }

                System.out.println();
            }

        }
    }
}
