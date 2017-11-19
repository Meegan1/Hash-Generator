package me.meegan.hash.util;

public class HashFunctionNotFoundException extends Exception {
    public HashFunctionNotFoundException() {
        super("Hash function could not be found.");
    }
}
