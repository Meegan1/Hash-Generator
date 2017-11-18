package me.meegan.hash.hashes;

public interface HashChecker {

    long produceFileHash(String filename);

    long produceDirHash(String path);

    long produceDirMetaHash(String path);
}

