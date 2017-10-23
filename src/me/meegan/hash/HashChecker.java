package me.meegan.hash;

public interface HashChecker {

    long produceFileHash(String filename);

    long produceDirHash(String path);

    long produceDirMetaHash(String path);
}

