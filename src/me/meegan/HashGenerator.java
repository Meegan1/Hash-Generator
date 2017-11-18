package me.meegan;

import me.meegan.hash.datastore.HashStore;

import java.io.FileNotFoundException;

public class HashGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        HashStore.loadData();
        HashDriver.ArgumentReader(args);
    }
}