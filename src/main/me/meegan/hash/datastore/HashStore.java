package me.meegan.hash.datastore;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class HashStore {
    private static ArrayList<HashEntry> hashList = loadData();
    private static final String HASH_FILE = "hash.store";

    public static boolean addEntry(String fileName, String hashFunction, long hash) {
        return addEntry(fileName, hashFunction, hash, false);
    }

    public static boolean addEntry(String fileName, String hashFunction, long hash, boolean isMeta) {
        if(hasEntry(fileName, hashFunction, isMeta))
            return false;

        hashList.add(new HashEntry(fileName, hashFunction, hash, isMeta));
        saveData();
        return true;
    }

    public static boolean removeEntry(String fileName, String hashFunction) {
        return removeEntry(fileName, hashFunction, false);
    }

    public static boolean removeEntry(String fileName, String hashFunction, boolean isMeta) {
        Iterator<HashEntry> iterator = hashList.iterator();
        while(iterator.hasNext()) {
            HashEntry entry = iterator.next();
            if(entry.getFileName().equals(fileName) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta) {
                iterator.remove();
                saveData();
                return true;
            }
        }
        return false;
    }

    public static boolean updateEntry(String fileName, String hashFunction, long hash) {
        return updateEntry(fileName, hashFunction, hash, false);
    }

    public static boolean updateEntry(String fileName, String hashFunction, long hash, boolean isMeta) {
        for(HashEntry entry : hashList) {
           if(entry.getFileName().equals(fileName) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta) {
               entry.setHash(hash);
               saveData();
               return true;
           }
        }
        return false;
    }

    public static boolean hasEntry(String fileName, String hashFunction) {
        return hasEntry(fileName, hashFunction, false);
    }

    public static boolean hasEntry(String fileName, String hashFunction, boolean isMeta) {
        for (HashEntry entry : hashList) {
            if(entry.getFileName().equals(fileName) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta)
                return true;
        }
        return false;
    }

    public static HashEntry getEntry(String fileName, String hashFunction) throws HashEntryNotFoundException {
        return getEntry(fileName, hashFunction, false);
    }

    public static HashEntry getEntry(String fileName, String hashFunction, boolean isMeta) throws HashEntryNotFoundException {
        for (HashEntry entry : hashList) {
            if(entry.getFileName().equals(fileName) && entry.getHashFunction().equals(hashFunction) && entry.isMeta() == isMeta)
                return entry;
        }
        throw new HashEntryNotFoundException();
    }

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
