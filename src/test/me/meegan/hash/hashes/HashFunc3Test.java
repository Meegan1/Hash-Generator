package me.meegan.hash.hashes;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashFunc3Test {
    @Test
    public void produceFileHash() throws Exception {
        System.out.println(String.format("File Hash: hash: %016X", new HashFunc3().produceFileHash("README.md")));
    }

    @Test
    public void produceDirHash() throws Exception {
        System.out.println(String.format("Dir Hash: hash: %016X", new HashFunc3().produceDirHash(".")));
    }

    @Test
    public void produceDirMetaHash() throws Exception {
        System.out.println(String.format("Dir Meta Hash: hash: %016X", new HashFunc3().produceDirMetaHash(".")));
    }

}