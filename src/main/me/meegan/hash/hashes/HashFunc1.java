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
            total += b * 29;
        }

        // total of byte values multiplied by (file size * a prime number)
        total *= length * 743;

        return total;
    }

    @Override
    public long produceDirHash(String path) {
        File directory = new File(path);

        // Get a list of all files and directories.
        File[] files = directory.listFiles();

        long hash = 0;

        if (files != null) {
            // iterate over files and directories
            for (File file : files) {
                // Test if file (or directory)
                boolean isFile = file.isFile();
                if ( isFile ) {
                    hash += produceFileHash(file.getAbsolutePath()); // run hash algorithm on file and add to total
                }
                else {
                    hash += produceDirHash(file.getAbsolutePath()); // recursive search through all files in folder
                }
            }
        }
        return hash;
    }

    @Override
    public long produceDirMetaHash(String path) {
        File directory = new File(path);

        // Get a list of all files and directories.
        File[] files = directory.listFiles();

        long total = 0;
        int totalSize = 0;

        if (files != null) {
            // iterate over files and directories
            for (File file : files) {
                // Test if file (or directory)
                boolean isFile = file.isFile();
                if ( isFile ) {
                    long filenameHash = 0;
                    for(char c : file.getName().toCharArray()) {
                        filenameHash += c * 29; // create a hash from the filename
                    }
                    total += (file.lastModified() * 29) + filenameHash ; // multiply last modified by prime number and add filenameHash
                    totalSize += file.length(); // add file size to totalSize
                }
                else {
                    total += produceDirMetaHash(file.getAbsolutePath()); // recursive search through all files in folder
                }
            }
        }

        total *= totalSize * 743; // multiply by totalSize of folder * prime number

        return total;
    }
}