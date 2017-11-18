package me.meegan.hash.datastore;

import java.io.Serializable;

public class HashEntry implements Serializable {
    private String fileName;
    private String hashFunction;
    private long hash;
    private boolean isMeta;


    public HashEntry(String fileName, String hashFunction, long hash) {
        this(fileName, hashFunction, hash, false);
    }

    public HashEntry(String fileName, String hashFunction, long hash, boolean isMeta) {
        this.fileName = fileName;
        this.hashFunction = hashFunction;
        this.hash = hash;
        this.isMeta = isMeta;
    }

    public String getFileName() {
        return fileName;
    }

    public String getHashFunction() {
        return hashFunction;
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public boolean isMeta() {
        return isMeta;
    }
}
