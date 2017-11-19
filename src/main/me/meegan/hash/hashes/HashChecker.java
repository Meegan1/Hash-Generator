package me.meegan.hash.hashes;

public interface HashChecker {

    /**
     * Produces a hash for an individual file
     *
     * @param filename absolute/relative path to file
     * @return a hash of the contents within file
     */
    long produceFileHash(String filename);

    /**
     * Produces a hash for the content within a directory
     *
     * @param path absolute/relative path to directory
     * @return a hash of the contents within folder
     */
    long produceDirHash(String path);

    /**
     * Produces a hash for the meta data within a directory
     *
     * @param path absolute/relative path to directory
     * @return a hash of the metadata within folder
     */
    long produceDirMetaHash(String path);
}

