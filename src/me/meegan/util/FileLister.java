package me.meegan.util;

import java.io.File;
import java.util.Date;

/**
 * Simple example of listing files and directories from a specific location, along with some useful attributes.
 *
 * @author mdixon
 *
 */
public class FileLister {

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