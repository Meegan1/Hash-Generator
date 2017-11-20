package me.meegan.hash.hashes;

import org.junit.*;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

public class HashFunc1Test {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Hash Function 1 Test:");
        System.out.println("-------------------");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("-------------------");
    }

    @Test
    public void produceFileHash() throws Exception {
        System.out.println(String.format("File Hash: hash: %016X", new HashFunc1().produceFileHash("README.md")));
    }

    @Test
    public void produceDirHash() throws Exception {
        System.out.println(String.format("Dir Hash: hash: %016X", new HashFunc1().produceDirHash(".")));
    }

    @Test
    public void produceDirMetaHash() throws Exception {
        System.out.println(String.format("Dir Meta Hash: hash: %016X", new HashFunc1().produceDirMetaHash(".")));
    }

    @Test (expected = NoSuchFileException.class)
    public void testInvalidFilePath() throws Exception {
        new HashFunc1().produceFileHash("invalidfile.invalid");
    }

    @Test (expected = FileNotFoundException.class)
    public void testInvalidDirPath() throws Exception {
        new HashFunc1().produceDirHash("/invalidfolder");
    }

    @Test (expected = FileNotFoundException.class)
    public void testInvalidDirMetaPath() throws Exception {
        new HashFunc1().produceDirMetaHash("/invalidfolder");
    }

}