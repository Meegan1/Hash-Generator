package me.meegan.hash.util;

/**
 * Thrown to indicate that the specified path is not a folder
 */
public class PathNotFolderException extends Exception {
    public PathNotFolderException() {
        super("Path specified is not a folder.");
    }
}
