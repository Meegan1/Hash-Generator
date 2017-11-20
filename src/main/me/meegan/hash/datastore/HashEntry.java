package me.meegan.hash.datastore;

import java.io.Serializable;

/**
 * Entry for HashStore
 */
public class HashEntry implements Serializable {
    private final String filename;
    private final String hashFunction;
    private long hash;
    private final boolean isMeta;


    /**
     * Creates new hash entry
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param hash hash of entry
     */
    public HashEntry(String filename, String hashFunction, long hash) {
        this(filename, hashFunction, hash, false);
    }

    /**
     * Creates new hash entry
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param hash hash of entry
     * @param isMeta is entry using metadata
     */
    public HashEntry(String filename, String hashFunction, long hash, boolean isMeta) {
        this.filename = filename;
        this.hashFunction = hashFunction;
        this.hash = hash;
        this.isMeta = isMeta;
    }

    /**
     * @return file name of entry
     */
    public String getFileName() {
        return filename;
    }

    /**
    * @return hash function of entry
     */
    public String getHashFunction() {
        return hashFunction;
    }

    /**
     * @return hash of entry
     */
    public long getHash() {
        return hash;
    }

    /**
     * Replaces the hash of entry with specified hash
     *
     * @param hash new hash for entry
     */
    public void setHash(long hash) {
        this.hash = hash;
    }

    /**
     * @return if entry uses metadata
     */
    public boolean isMeta() {
        return isMeta;
    }
}
