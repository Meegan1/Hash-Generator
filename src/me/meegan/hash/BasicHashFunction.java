package me.meegan.hash;

import java.io.IOException;

public class BasicHashFunction implements HashChecker {
    @Override
    public long produceFileHash(String filename) {
        byte[] bytes = new byte[0];
        try {
            bytes = me.meegan.util.FileReader.readFileContent(filename);
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
        return 0;
    }

    @Override
    public long produceDirMetaHash(String path) {
        return 0;
    }
}
