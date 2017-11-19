package me.meegan.hash.datastore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HashStoreTest {
    @Before
    public void setUp() throws Exception {
        // sets up default entries
        HashStore.addEntry(".", "HashFunc1", 10203, false);
    }

    @After
    public void tearDown() throws Exception {
        // removes default entries
        HashStore.removeEntry(".", "HashFunc1", false);
    }

    @Test
    public void testAddEntry() throws Exception {
        HashStore.addEntry("README.md", "HashFunc1", 40123);
        assertEquals(40123, HashStore.getEntry("README.md", "HashFunc1").getHash());
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