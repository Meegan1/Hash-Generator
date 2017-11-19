package me.meegan.hash.hashes;

import me.meegan.hash.util.FileUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by c3497521 on 16/11/2017.
 */
public class HashFunc2 implements HashChecker{

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
            total += (b << 0xF) * 7;
            total <<= 0xF;
        }
        // adds modulus of length and character in middle of file content
        total += bytes.length % ((bytes[bytes.length/2] != 0) ? bytes[bytes.length/2] : 1); // if middle character = 0, return 1
        total *= length * 557;

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
                    for(char c : file.getName().toCharArray()) { // create a hash from the filename
                        filenameHash += (c << 0xF) * 7; // bit-shift character and multiply by prime
                        filenameHash <<= 0xF; // bit-shift whole hash
                    }
                    total += (file.lastModified() % 13) + filenameHash ; // get modulus of last modified and prime number then add to filenameHash
                    int middleChar = file.getName().charAt(file.getName().length()/2); // gets character value in middle of file name
                    total += file.length() % (middleChar != 0 ? middleChar : 1); // add modulus of file length and char value in middle of file name to total (if 0, sets to 1)
                    totalSize += file.length();
                }
                else {
                    total += produceDirMetaHash(file.getAbsolutePath()); // recursive search through all files in folder
                }
            }
        }

        total *= totalSize * 557; // multiply by totalSize of folder * prime number

        return total;
    }
}
