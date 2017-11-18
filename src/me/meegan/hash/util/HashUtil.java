package me.meegan.hash.util;

import me.meegan.hash.datastore.HashStore;
import me.meegan.hash.hashes.HashChecker;
import me.meegan.hash.hashes.HashFunc1;

import java.io.File;
import java.io.FileNotFoundException;

public class HashUtil {
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

    private static HashChecker getHashFunction(String hashFunction) throws HashFunctionNotFoundException {
        try {
            Class c = Class.forName("me.meegan.hash.hashes." + hashFunction);
            return (HashChecker) c.newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new HashFunctionNotFoundException();
        }
    }
}
