package me.meegan.hash.hashes;

import org.junit.Test;

public class HashFunc2Test {
    @Test
    public void produceFileHash() throws Exception {
        System.out.println(String.format("File Hash: hash: %016X", new HashFunc2().produceFileHash("README.md")));
    }

    @Test
    public void produceDirHash() throws Exception {
        System.out.println(String.format("Dir Hash: hash: %016X", new HashFunc2().produceDirHash(".")));
    }

    @Test
    public void produceDirMetaHash() throws Exception {
        System.out.println(String.format("Dir Meta Hash: hash: %016X", new HashFunc2().produceDirMetaHash(".")));
    }
}