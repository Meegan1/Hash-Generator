package me.meegan;

import me.meegan.hash.datastore.HashStore;
import me.meegan.hash.util.HashUtil;
import org.junit.*;

import static org.junit.Assert.*;

public class HashDriverTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println("Hash Driver Test:");
        System.out.println("-------------------");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("-------------------");
    }

    @Before
    public void setUp() throws Exception {
        HashStore.addEntry("README.md", "HashFunc1", 1234);
        HashStore.addEntry(".", "HashFunc2", 4321);
        HashStore.addEntry(".", "HashFunc3", 3124, true);
    }

    @After
    public void tearDown() {
        HashStore.removeEntry("README.md", "HashFunc1");
        HashStore.removeEntry(".", "HashFunc2");
        HashStore.removeEntry(".", "HashFunc3", true);
    }

    //======================================[File Hash]=================================================
    @Test
    public void testAddFile() throws Exception {
        tearDown();
        String[] args = {"README.md", "-HashFunc1"};
        HashDriver.ArgumentReader(args);

        assertTrue(HashStore.hasEntry("README.md", "HashFunc1"));
    }

    @Test
    public void testAddExistingFile() throws Exception {
        String[] args = {"README.md", "-HashFunc1"};
        HashDriver.ArgumentReader(args);

        assertTrue(HashStore.hasEntry("README.md", "HashFunc1"));

        assertNotEquals(1234, HashUtil.generateHash("README.md", "HashFunc1", false)); // checks file has been changed
    }

    @Test
    public void testRemoveFile() throws Exception {
        String[] args = {"README.md", "-HashFunc1", "-remove"};
        HashDriver.ArgumentReader(args);

        assertFalse(HashStore.hasEntry("README.md", "HashFunc1"));
    }

    @Test
    public void testReplaceFile() throws Exception {
        String[] args = {"README.md", "-HashFunc1", "-replace"};
        HashDriver.ArgumentReader(args);

        assertNotEquals(1234, HashStore.getEntry("README.md", "HashFunc1").getHash());
    }

    //======================================[Dir Hash]=================================================
    @Test
    public void testAddDir() throws Exception {
        tearDown();
        String[] args = {".", "-HashFunc2"};
        HashDriver.ArgumentReader(args);

        assertTrue(HashStore.hasEntry(".", "HashFunc2"));
    }

    @Test
    public void testAddExistingDir() throws Exception {
        String[] args = {".", "-HashFunc2"};
        HashDriver.ArgumentReader(args);

        assertTrue(HashStore.hasEntry(".", "HashFunc2"));

        assertNotEquals(4321, HashUtil.generateHash(".", "HashFunc2", false)); // checks directory has been changed
    }

    @Test
    public void testRemoveDir() throws Exception {
        String[] args = {".", "-HashFunc2", "-remove"};
        HashDriver.ArgumentReader(args);

        assertFalse(HashStore.hasEntry(".", "HashFunc2"));
    }

    @Test
    public void testReplaceDir() throws Exception {
        String[] args = {".", "-HashFunc2", "-replace"};
        HashDriver.ArgumentReader(args);

        assertNotEquals(4321, HashStore.getEntry(".", "HashFunc2").getHash());
    }

    //======================================[Dir Meta Hash]=================================================
    @Test
    public void testAddDirMeta() throws Exception {
    tearDown();
    String[] args = {".", "-HashFunc3", "-meta"};
    HashDriver.ArgumentReader(args);

    assertTrue(HashStore.hasEntry(".", "HashFunc3", true));
}

    @Test
    public void testAddExistingDirMeta() throws Exception {
        String[] args = {".", "-HashFunc3", "-meta"};
        HashDriver.ArgumentReader(args);

        assertTrue(HashStore.hasEntry(".", "HashFunc3", true));

        assertNotEquals(3124, HashUtil.generateHash(".", "HashFunc3", true)); // checks directory metadata has been changed
    }

    @Test
    public void testRemoveDirMeta() throws Exception {
        String[] args = {".", "-HashFunc3", "-remove", "-meta"};
        HashDriver.ArgumentReader(args);

        assertFalse(HashStore.hasEntry(".", "HashFunc3", true));
    }

    @Test
    public void testReplaceDirMeta() throws Exception {
        String[] args = {".", "-HashFunc3", "-replace", "-meta"};
        HashDriver.ArgumentReader(args);

        assertNotEquals(3124, HashStore.getEntry(".", "HashFunc3", true).getHash());
    }

}