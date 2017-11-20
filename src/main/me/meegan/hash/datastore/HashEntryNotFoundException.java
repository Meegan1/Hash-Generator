package me.meegan.hash.datastore;

/**
 * Thrown when a hash entry cannot be found
 */
public class HashEntryNotFoundException extends Exception {
    public HashEntryNotFoundException() {
        super("Hash entry could not be found.");
    }
}
