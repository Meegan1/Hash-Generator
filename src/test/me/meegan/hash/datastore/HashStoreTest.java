package me.meegan.hash.datastore;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class HashStoreTest {

    @Test
    public void testAddEntry() throws Exception {
        HashStore.addEntry(".", "HashFunc1", 10203, false);
        assertEquals(10203, HashStore.getEntry(".", "HashFunc1").getHash());
    }

    @Test
    public void testUpdateEntry() throws Exception {
        HashStore.updateEntry(".", "HashFunc1", 10204, false);
        assertEquals(10204, HashStore.getEntry(".", "HashFunc1").getHash());
    }

    @Test
    public void testRemoveEntry() throws Exception {
        HashStore.removeEntry(".", "HashFunc1");
        assertFalse(HashStore.hasEntry(".", "HashFunc1"));
    }

}