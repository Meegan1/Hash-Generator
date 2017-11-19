package me.meegan.hash.util;

public class PathNotFolderException extends Exception {
    public PathNotFolderException() {
        super("Path specified is not a folder.");
    }
}
