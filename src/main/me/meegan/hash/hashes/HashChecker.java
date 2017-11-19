package me.meegan.hash.hashes;

import java.io.IOException;

public interface HashChecker {

    /**
     * Produces a hash for an individual file
     *
     * @param filename absolute/relative path to file
     * @return a hash of the contents within file
     * @throws IOException if problem with specified file
     */
    long produceFileHash(String filename) throws IOException;

    /**
     * Produces a hash for the content within a directory
     *
     * @param path absolute/relative path to directory
     * @return a hash of the contents within folder
     * @throws IOException if problem with specified directory
     */
    long produceDirHash(String path) throws IOException;

    /**
     * Produces a hash for the meta data within a directory
     *
     * @param path absolute/relative path to directory
     * @return a hash of the metadata within folder
     * @throws IOException if problem with specified directory
     */
    long produceDirMetaHash(String path) throws IOException;
}

