package me.meegan;

import me.meegan.util.ArgumentReader;
import me.meegan.util.FileLister;

public class HashGenerator {
    public static void main(String[] args) {
        ArgumentReader arg = new ArgumentReader(args);
        FileLister.list(".");
    }
}
