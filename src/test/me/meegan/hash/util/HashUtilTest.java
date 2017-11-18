package me.meegan.hash.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashUtilTest {
    @Test
    public void generateFileHash() throws Exception {
        long hash = HashUtil.generateHash("README.md", "HashFunc1", false);
        assertTrue(hash != 0);
        System.out.println("Hash: " + hash);
    }

    @Test
    public void generateFolderHash() throws Exception {
        long hash = HashUtil.generateHash(".", "HashFunc1", false);
        assertTrue(hash != 0);
        System.out.println("Hash: " + hash);
    }

    @Test
    public void generateFolderMetaHash() throws Exception {
        long hash = HashUtil.generateHash(".", "HashFunc1", true);
        assertTrue(hash != 0);
        System.out.println("Hash: " + hash);
    }
}