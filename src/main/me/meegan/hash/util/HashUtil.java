package me.meegan.hash.util;

import me.meegan.hash.hashes.HashChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Utility class for hash generation
 */
public class HashUtil {
    /**
     * Generates a hash from the given parameters
     *
     * @param filename absolute/relative path to file/folder
     * @param hashFunction case-sensitive name of hash function
     * @param isMeta whether meta data should be used instead of content
     * @return a hash for the given file/folder
     * @throws FileNotFoundException if file/folder cannot be found
     * @throws HashFunctionNotFoundException if hash function cannot be found
     * @throws PathNotFolderException if requesting meta hash on a path that is not a folder
     */
    public static long generateHash(String filename, String hashFunction, boolean isMeta) throws IOException, HashFunctionNotFoundException, PathNotFolderException {
        if(filename == null) // check if no filename has been given
            throw new FileNotFoundException("No file/directory has been specified.");

        // check if this is a known file or directory.
        File file = new File(filename);
        if (file.exists()) {
            HashChecker checker = getHashFunction(hashFunction);

            if(file.isFile()) {
                if(isMeta)
                    throw new PathNotFolderException();
                return checker.produceFileHash(filename);
            }
            else if (file.isDirectory())
                return isMeta ? checker.produceDirMetaHash(filename) : checker.produceDirHash(filename);
        }

        throw new FileNotFoundException("Specified file/directory does not exist: " + filename + ".");
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
