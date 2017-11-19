package me.meegan.hash.util;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashUtilTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("HashUtil Test:");
        System.out.println("-------------------");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("-------------------");
    }

    @Test
    public void generateFileHash() throws Exception {
        long hash = HashUtil.generateHash("README.md", "HashFunc1", false);
        assertTrue(hash != 0);
        System.out.println("File Hash: " + hash);
    }

    @Test
    public void generateFolderHash() throws Exception {
        long hash = HashUtil.generateHash(".", "HashFunc1", false);
        assertTrue(hash != 0);
        System.out.println("Folder Hash: " + hash);
    }

    @Test
    public void generateFolderMetaHash() throws Exception {
        long hash = HashUtil.generateHash(".", "HashFunc1", true);
        assertTrue(hash != 0);
        System.out.println("Folder Meta Hash: " + hash);
    }
}