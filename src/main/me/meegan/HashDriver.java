package me.meegan;

import me.meegan.hash.datastore.HashEntryNotFoundException;
import me.meegan.hash.datastore.HashStore;
import me.meegan.hash.util.HashFunctionNotFoundException;
import me.meegan.hash.util.PathNotFolderException;

import java.io.File;
import java.io.FileNotFoundException;

import static me.meegan.hash.util.HashUtil.generateHash;


/**
 * Driver for Hash Generator Command-Line Application
 */
public class HashDriver {


    /**
     * Static method that checks if a specified option is present in the given argument list.
     *
     * @param option the option to be tested for, usually options are specified with a '-' prefix.
     * @param args the argument array
     * @return true if option present, else false.
     */
    private static boolean optionPresent(String option, String[] args) {
        for (String arg : args ) {
            if ( arg.equalsIgnoreCase(option))
                return true;
        }
        return false;
    }

    /**
     *  Reads the arguments from args parameter and runs specified options
     *
     * @param args the argument array
     */
    public static void ArgumentReader(String[] args) {
        if ( args.length > 0 ) {
            String hashFunction = "HashFunc1"; // default to HashFunc1
            String filename = null;
            for (String arg : args) {
                if (arg.startsWith("-")) { // check for options
                    // check if option is a hash function
                    if (!arg.equals("-meta") && !arg.equals("-replace") && !arg.equals("-remove"))
                        // get hash function
                        hashFunction = arg.replace("-", "");
                } else
                    filename = arg; // get filename
            }

            try {
                boolean isMeta = optionPresent("-meta", args);
                long hash = generateHash(filename, hashFunction, isMeta);
                boolean isDirectory = new File(filename).isDirectory();
                System.out.println(String.format((isDirectory ? ((isMeta) ? "Directory (meta)" : "Directory") : "File") + " \"%s\"  hash: %016X", filename, hash)); // print file-type and hash

                if (optionPresent("-replace", args))
                    if (HashStore.updateEntry(filename, hashFunction, hash))
                        System.out.println("Result: Entry replaced within data file.");
                    else
                        System.err.println("Entry does not exist.");

                else if (optionPresent("-remove", args))
                    if (HashStore.removeEntry(filename, hashFunction))
                        System.out.println("Result: Entry removed from data file.");
                    else
                        System.err.println("Entry does not exist.");
                else
                    try {
                        if (HashStore.getEntry(filename, hashFunction, isMeta).getHash() == hash)
                            System.out.println("Result: File contents have not been changed.");
                        else
                            System.out.println("Result: Warning – file contents have been changed.");
                    } catch (HashEntryNotFoundException e) {
                        HashStore.addEntry(filename, hashFunction, hash, isMeta);
                        System.out.println("Result: New entry added to data file.");
                    }

            } catch (FileNotFoundException | HashFunctionNotFoundException | PathNotFolderException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        }
        else
            System.out.println("No Arguments specified");
    }
}