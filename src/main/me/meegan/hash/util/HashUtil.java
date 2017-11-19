package me.meegan.hash.util;

import me.meegan.hash.hashes.HashChecker;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Utility class for hash generation
 */
public class HashUtil {
    /**
     * Generates a hash from the given parameters
     *
     * @param file absolute/relative path to file/folder
     * @param hashFunction case-sensitive name of hash function
     * @param isMeta whether meta data should be used instead of content
     * @return a hash for the given file/folder
     * @throws FileNotFoundException if file/folder cannot be found
     * @throws HashFunctionNotFoundException if hash function cannot be found
     */
    public static long generateHash(String file, String hashFunction, boolean isMeta) throws FileNotFoundException, HashFunctionNotFoundException {
        // check if this is a known file or directory.
        File next = new File(file);
        if (next.exists()) {
            HashChecker checker = getHashFunction(hashFunction);

            if(next.isFile())
                return checker.produceFileHash(file);
            else if (next.isDirectory())
                return isMeta ? checker.produceDirMetaHash(file) : checker.produceDirHash(file);
        }

        throw new FileNotFoundException();
    }

    /**
     * Uses Reflection API to return a HashChecker object from a given string
     *
     * @param hashFunction case-sensitive name of hash function
     * @return the hash function object
     * @throws HashFunctionNotFoundException if hash function isn't found
     */
    private static HashChecker getHashFunction(String hashFunction) throws HashFunctionNotFoundException {
        try {
            Class c = Class.forName("me.meegan.hash.hashes." + hashFunction);
            return (HashChecker) c.newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new HashFunctionNotFoundException();
        }
    }
}
