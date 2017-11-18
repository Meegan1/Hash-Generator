package me.meegan.hash.hashes;

/**
 * Created by c3497521 on 16/11/2017.
 */
public class HashFunc2 implements HashChecker{

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
