package me.meegan.hash.datastore;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Datastore for hash entries
 */
public class HashStore {
    private static ArrayList<HashEntry> hashList = loadData();
    private static final String HASH_FILE = "hash.store";

    /**
     * adds entry to datastore if it doesn't exits
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param hash hash of entry
     * @return whether entry exists
     */
    public static boolean addEntry(String filename, String hashFunction, long hash) {
        return addEntry(filename, hashFunction, hash, false);
    }

    /**
     * adds entry to datastore if it doesn't exits
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param hash hash of entry
     * @param isMeta whether entry uses metadata
     * @return whether entry exists
     */
    public static boolean addEntry(String filename, String hashFunction, long hash, boolean isMeta) {
        if(hasEntry(filename, hashFunction, isMeta))
            return false;

        hashList.add(new HashEntry(filename, hashFunction, hash, isMeta));
        saveData();
        return true;
    }

    /**
     * Searches for entry and attempts to remove it from the datastore
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @return whether removal was successful
     */
    public static boolean removeEntry(String filename, String hashFunction) {
        return removeEntry(filename, hashFunction, false);
    }

    /**
     * Searches for entry and attempts to remove it from the datastore
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param isMeta whether entry uses metadata
     * @return whether removal was successful
     */
    public static boolean removeEntry(String filename, String hashFunction, boolean isMeta) {
        Iterator<HashEntry> iterator = hashList.iterator();
        while(iterator.hasNext()) {
            HashEntry entry = iterator.next();
            if(entry.getFileName().equals(filename) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta) {
                iterator.remove();
                saveData();
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for entry and attempts to update it
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param hash new hash for entry
     * @return whether update was successful
     */
    public static boolean updateEntry(String filename, String hashFunction, long hash) {
        return updateEntry(filename, hashFunction, hash, false);
    }

    /**
     * Searches for entry and attempts to update it
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param hash new hash for entry
     * @param isMeta whether entry uses metadata
     * @return whether removal was successful
     */
    public static boolean updateEntry(String filename, String hashFunction, long hash, boolean isMeta) {
        for(HashEntry entry : hashList) {
           if(entry.getFileName().equals(filename) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta) {
               entry.setHash(hash);
               saveData();
               return true;
           }
        }
        return false;
    }

    /**
     * Checks whether entry exists in datastore
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @return whether entry exists
     */
    public static boolean hasEntry(String filename, String hashFunction) {
        return hasEntry(filename, hashFunction, false);
    }

    /**
     * Checks whether entry exists in datastore
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param isMeta whether entry uses metadata
     * @return whether entry exists
     */
    public static boolean hasEntry(String filename, String hashFunction, boolean isMeta) {
        for (HashEntry entry : hashList) {
            if(entry.getFileName().equals(filename) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta)
                return true;
        }
        return false;
    }

    /**
     * Gets {@link HashEntry} from datastore
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @return HashEntry object of specified entry
     * @throws HashEntryNotFoundException if entry does not exist
     */
    public static HashEntry getEntry(String filename, String hashFunction) throws HashEntryNotFoundException {
        return getEntry(filename, hashFunction, false);
    }

    /**
     * Gets {@link HashEntry} from datastore
     *
     * @param filename absolute/relative path to file
     * @param hashFunction specific hash function for entry
     * @param isMeta whether entry uses metadata
     * @return HashEntry object of specified entry
     * @throws HashEntryNotFoundException if entry does not exist
     */
    public static HashEntry getEntry(String filename, String hashFunction, boolean isMeta) throws HashEntryNotFoundException {
        for (HashEntry entry : hashList) {
            if(entry.getFileName().equals(filename) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta)
                return entry;
        }
        throw new HashEntryNotFoundException();
    }

    /**
     * Serializes hashList into HASH_FILE file
     */
    private static void saveData() {
        try {
            FileOutputStream fout = new FileOutputStream(HASH_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(hashList);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return deserialized hashList from HASH_FILE
     */
    @SuppressWarnings("unchecked")
    private static ArrayList<HashEntry> loadData() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(HASH_FILE));
            return (ArrayList<HashEntry>) in.readObject(); // return list from HASH_FILE
        } catch (ClassNotFoundException | IOException e) {
            return new ArrayList<>(); // return empty/new list if fails
        }
    }
}
