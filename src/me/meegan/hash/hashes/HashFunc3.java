package me.meegan.hash.hashes;

public class HashFunc3 implements HashChecker{

    @Override
    public long produceFileHash(String filename) {
        return 25;
    }

    @Override
    public long produceDirHash(String path) {
        return 0;
    }

    @Override
    public long produceDirMetaHash(String path) {
        return 0;
    }
}
