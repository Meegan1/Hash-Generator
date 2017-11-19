package me.meegan.hash.hashes;

import me.meegan.hash.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HashFunc3 implements HashChecker{
    @Override
    public long produceFileHash(String filename) throws IOException {
        byte[] bytes;
        bytes = FileUtil.readFileContent(filename);

        int length = bytes.length;

        long total = 0;
        long total2 = 0; // secondary hash

        // Dump byte array contents and total the values.
        for (byte b : bytes) {
            total += b * 5;
            total2 += (((b % 11)) * 17) * b;
        }

        total2 *= bytes[0] + bytes[bytes.length-1] + 1; // multiply secondary hash by (first character in file + last)+1

        total2 <<= 0xF; // bit-shift secondary hash left 0xF

        total += total2 * 11; // add secondary hash to main hash and multiply by prime number

        // total of byte values multiplied by (file size * a prime number)
        total *= length * 743;

        return total;
    }

    @Override
    public long produceDirHash(String path) throws IOException {
        File directory = new File(path);

        if(!directory.exists())
            throw new FileNotFoundException("Specified directory does not exist: " + path + ".");

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
    public long produceDirMetaHash(String path) throws IOException {
        File directory = new File(path);

        if(!directory.exists())
            throw new FileNotFoundException("Specified directory does not exist: " + path + ".");

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
                    long filenameHash2 = 0;
                    char[] filename = file.getName().toCharArray();
                    for(char c : filename) {
                        filenameHash += c * 5; // create a hash from the filename
                        filenameHash2 += (((c % 11)) * 17) * c;
                    }
                    filenameHash2 *= filename[0] + filename[filename.length-1] + 1; // multiply by (first char of name + last char of name + 1)
                    total += filenameHash * 11;
                    total += filenameHash2 * 11;
                    total += (file.lastModified() * 13); // multiply last modified by prime number
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
