package me.meegan.hash.datastore;

public class HashEntryNotFoundException extends Exception {
    public HashEntryNotFoundException() {
        super("Hash entry could not be found.");
    }
}
