package me.meegan.hash.util;

import me.meegan.hash.hashes.HashChecker;
import me.meegan.hash.hashes.HashFunc1;

import java.io.File;

public class HashUtil {
    public static long generateHash(String file, String hashFunction, boolean isMetaHash) {
        // check if this is a known file or directory.
        File next = new File(file);
        if ( next.exists() ) {
            HashChecker hash = new HashFunc1();
            try {
                Class c = Class.forName("me.meegan.hash." + hashFunction);
                hash = (HashChecker) c.newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                System.out.println("Invalid hash function");
            }
            if ( next.isFile() ) {
                // found a file
                System.out.println("Valid file name detected : " + file);

                return hash.produceFileHash(file);
            }
            else if ( next.isDirectory() ) {
                // found a directory.
                System.out.println("Valid directory name detected : " + file);
                return isMetaHash ? hash.produceDirMetaHash(file) : hash.produceDirHash(file);
            }
        }
        else {
            System.err.println("Specified file/directory name does not exist : " + file);
        }
        return -1;
    }
}
