package me.meegan.hash.datastore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashEntryTest {
    private static HashEntry fileEntry;
    private static HashEntry dirEntry;
    private static HashEntry dirMetaEntry;

    @Before
    public void setUp() throws Exception {
        fileEntry = new HashEntry("README.md", "HashFunc1", 1234, false);
        dirEntry = new HashEntry(".", "HashFunc2", 5678, false);
        dirMetaEntry = new HashEntry(".", "HashFunc3", 9123, true);
    }

    @Test
    public void getFileName() throws Exception {
        assertEquals("README.md", fileEntry.getFileName());
        assertEquals(".", dirEntry.getFileName());
        assertEquals(".", dirMetaEntry.getFileName());
    }

    @Test
    public void getHashFunction() throws Exception {
        assertEquals("HashFunc1", fileEntry.getHashFunction());
        assertEquals("HashFunc2", dirEntry.getHashFunction());
        assertEquals("HashFunc3", dirMetaEntry.getHashFunction());
    }

    @Test
    public void getHash() throws Exception {
        assertEquals(1234, fileEntry.getHash());
        assertEquals(5678, dirEntry.getHash());
        assertEquals(9123, dirMetaEntry.getHash());
    }

    @Test
    public void setHash() throws Exception {
        dirEntry.setHash(5619);
        assertNotEquals(5678, dirEntry.getHash());
        assertEquals(5619, dirEntry.getHash());
    }

    @Test
    public void isMeta() throws Exception {
        assertFalse(fileEntry.isMeta());
        assertFalse(dirEntry.isMeta());
        assertTrue(dirMetaEntry.isMeta());
    }

}