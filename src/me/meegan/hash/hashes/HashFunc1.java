package me.meegan.hash.hashes;

import me.meegan.hash.util.FileUtil;

import java.io.File;
import java.io.IOException;

public class HashFunc1 implements HashChecker {
    @Override
    public long produceFileHash(String filename) {
        byte[] bytes = new byte[0];
        try {
            bytes = FileUtil.readFileContent(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int length = bytes.length;

        long total = 0;

        // Dump byte array contents and total the values.
        for (byte b : bytes) {
            total += b * 13;
        }

        // create a very simple hash (total of byte values, each multiplied by a prime number, all of which is multiplied by file size)
        total *= length * 997;

        return total;
    }

    @Override
    public long produceDirHash(String path) {
        // Would normally use a specific path, for now just use current directory.
        File directory = new File(path);

        // Get a list of all files and directories.
        File[] files = directory.listFiles();

        long hash = 0;

        if (files != null) {
            // iterate over files and directories
            for (File next : files) {
                // Test if file (or directory)
                boolean isFile = next.isFile();
                if ( isFile ) {
                    hash += produceFileHash(next.getAbsolutePath());
                }
                else {
                    hash += produceDirHash(next.getAbsolutePath());
                }
            }
        }
        return hash;
    }

    @Override
    public long produceDirMetaHash(String path) {
        // Would normally use a specific path, for now just use current directory.
        File directory = new File(path);

        // Get a list of all files and directories.
        File[] files = directory.listFiles();

        long hash = 0;

        if (files != null) {
            // iterate over files and directories
            for (File next : files) {
                // Test if file (or directory)
                boolean isFile = next.isFile();
                if ( isFile ) {
                    hash += next.lastModified();
                }
                else {
                    hash += produceDirMetaHash(next.getAbsolutePath());
                }
            }
        }
        return hash;
    }
}