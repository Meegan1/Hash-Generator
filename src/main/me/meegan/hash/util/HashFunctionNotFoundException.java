package me.meegan.hash.util;

/**
 * Thrown when a hash function cannot be found
 */
public class HashFunctionNotFoundException extends Exception {
    public HashFunctionNotFoundException() {
        super("Specified hash function does not exist.");
    }
}
