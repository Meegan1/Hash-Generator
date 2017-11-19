package me.meegan.hash.util;

import org.junit.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.Assert.*;

public class FileUtilTest {
    @Test
    public void readFileContent() throws Exception {
        byte[] bytes = FileUtil.readFileContent("README.md");
        assertEquals("Hash Generator", new String(bytes));
    }

    @Test (expected = NoSuchFileException.class)
    public void readInvalidFileContent() throws Exception {
        byte[] bytes = FileUtil.readFileContent("invalidfile.invalid");
    }

}