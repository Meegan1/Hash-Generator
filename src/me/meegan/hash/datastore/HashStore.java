package me.meegan.hash.datastore;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class HashStore {
    private static ArrayList<HashEntry> hashList = new ArrayList<>();
    private static final String HASH_FILE = "hash.store";

    public static boolean addEntry(String fileName, String hashFunction, long hash) {
        return addEntry(fileName, hashFunction, hash, false);
    }

    public static boolean addEntry(String fileName, String hashFunction, long hash, boolean isMeta) {
        if(hasEntry(fileName, hashFunction))
            return false;

        hashList.add(new HashEntry(fileName, hashFunction, hash, isMeta));
        saveData();
        return true;
    }

    public static boolean removeEntry(String fileName, String hashFunction) {
        Iterator<HashEntry> iterator = hashList.iterator();
        while(iterator.hasNext()) {
            HashEntry entry = iterator.next();
            if(entry.getFileName().equals(fileName) && entry.getHashFunction().equals(hashFunction)) {
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
        for(HashEntry hashEntry : hashList) {
           if(hashEntry.getFileName().equals(fileName) && hashEntry.getHashFunction().equals(hashFunction) && hashEntry.isMeta() == isMeta) {
               hashEntry.setHash(hash);
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
        for (HashEntry hashEntry : hashList) {
            if(hashEntry.getFileName().equals(fileName) && hashEntry.getHashFunction().equals(hashFunction) && hashEntry.isMeta() == isMeta)
                return true;
        }
        return false;
    }

    public static HashEntry getEntry(String fileName, String hashFunction) {
        return getEntry(fileName, hashFunction, false);
    }

    public static HashEntry getEntry(String fileName, String hashFunction, boolean isMeta) {
        for (HashEntry hashEntry : hashList) {
            if(hashEntry.getFileName().equals(fileName) && hashEntry.getHashFunction().equals(hashFunction) && hashEntry.isMeta() == isMeta)
                return hashEntry;
        }
        return null;
    }

    public static void saveData() {
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
    public static void loadData() throws FileNotFoundException {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(HASH_FILE));
            hashList = (ArrayList<HashEntry>) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
