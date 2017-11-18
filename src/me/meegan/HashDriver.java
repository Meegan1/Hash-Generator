package me.meegan;

import me.meegan.hash.hashes.HashFunc1;
import me.meegan.hash.hashes.HashChecker;

import java.io.File;

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
            String hashFunction = null;
            String file = null;
            boolean isMetaHash = optionPresent("-meta", args);
            for (String arg : args ) {
                System.out.println("Found argument " + arg);
                if ( arg.startsWith("-") ) {
                    // must be an option, so check
                    if ( optionPresent("-help", args) ) {
                        // Help option specified
                        System.out.println("Help option detected");
                    }
                    else if(!arg.equals("-meta"))
                        hashFunction = arg.replace("-", "");
                }
                else {
                    file = arg;
                }
            }
            System.out.println(String.format("Hash value is %016X", generateHash(file, hashFunction, isMetaHash)));
        }
        else {
            System.out.println("No Arguments specified");
        }

    }
}