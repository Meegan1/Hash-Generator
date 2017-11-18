package me.meegan;

import me.meegan.hash.datastore.HashStore;
import me.meegan.hash.util.HashFunctionNotFoundException;

import java.io.FileNotFoundException;

import static me.meegan.hash.util.HashUtil.generateHash;


/**
 * Shows hows to read arguments specified by the user on command line.
 *
 * @author mdixon
 *
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
     *
     *
     * @param args the argument array
     */
    public static void ArgumentReader(String[] args) {
        if ( args.length > 0 ) {
            String hashFunction = "HashFunc1";
            String fileName = null;
            for (String arg : args) {
                System.out.println("Found argument " + arg);
                if (arg.startsWith("-")) {
                    // must be an option, so check
                    if (optionPresent("-help", args)) {
                        // Help option specified
                        System.out.println("Help option detected");
                    } else if (!arg.equals("-meta") && !arg.equals("-replace") && !arg.equals("-remove"))
                        hashFunction = arg.replace("-", "");
                } else
                    fileName = arg;
            }

            try {
                long hash = generateHash(fileName, hashFunction, optionPresent("-meta", args));
                System.out.println(String.format("File “%s”  hash: %016X", fileName, hash));

                if (optionPresent("-replace", args))
                    if (HashStore.updateEntry(fileName, hashFunction, hash))
                        System.out.println("Result: Entry replaced within data file.");
                    else
                        System.err.println("Entry does not exist.");

                else if (optionPresent("-remove", args))
                    if (HashStore.removeEntry(fileName, hashFunction))
                        System.out.println("Result: Entry removed from data file.");
                    else
                        System.err.println("Entry does not exist.");

                else if (HashStore.addEntry(fileName, hashFunction, hash))
                    System.out.println("Result: New entry added to data file.");

                else
                    if (HashStore.getEntry(fileName, hashFunction).getHash() == hash)
                        System.out.println("Result: File contents have not been changed.");
                    else
                        System.out.println("Result: Warning – file contents have been changed.");

            } catch (FileNotFoundException e) {
                System.err.println("Specified file/directory name does not exist : " + fileName);
            } catch (HashFunctionNotFoundException e) {
                System.err.println("Specified hash function does not exist.");
            }
        }
        else
            System.out.println("No Arguments specified");
    }
}