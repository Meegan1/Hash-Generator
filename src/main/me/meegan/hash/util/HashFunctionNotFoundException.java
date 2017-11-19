package me.meegan.hash.util;

public class HashFunctionNotFoundException extends Exception {
    public HashFunctionNotFoundException() {
        super("Specified hash function does not exist.");
    }
}
