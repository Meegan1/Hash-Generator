package me.meegan.util;

import me.meegan.hash.BasicHashFunction;

import java.io.File;


/**
 * Shows hows to read arguments specified by the user on command line.
 *
 * @author mdixon
 *
 */
public class ArgumentReader {


    /**
     * Static method that checks if a specified option is present in the given argument list.
     *
     * @param option the option to be tested for, usually options are specified with a '-' prefix.
     * @param args the argument array
     * @return true if option present, else false.
     */
    static boolean optionPresent(String option, String [] args) {

        for (String arg : args ) {
            if ( arg.equalsIgnoreCase(option))
                return true;
        }

        return false;
    }

    /**
     * The args value contains additional command line values the user specified when running the program, e.g.
     *
     * java ArgumentReader -help someFileName someDirectoryName
     *
     * In Eclipse right click on the file, and select Run As -> Run Configurations, then set the values in the "Arguments" tab
     *
     * @param args
     */
    public ArgumentReader(String[] args) {

        if ( args.length > 0 ) {

            for (String arg : args ) {
                System.out.println("Found argument " + arg);

                if ( arg.startsWith("-") ) {
                    // must be an option, so check

                    if ( optionPresent("-help", args) ) {
                        // Help option specified
                        System.out.println("Help option detected");
                    }
                    else {
                        System.err.println("Ignoring unknown option : " + arg);
                    }
                }
                else {
                    // check if this is a known file or directory.
                    File next = new File(arg);

                    if ( next.exists() ) {

                        if ( next.isFile() ) {
                            // found a file
                            System.out.println("Valid file name detected : " + arg);

                            BasicHashFunction hash = new BasicHashFunction();
                            System.out.println(String.format("Hash value is %016X", hash.produceFileHash(arg)));
                        }
                        else if ( next.isDirectory() ) {
                            // found a directory.
                            System.out.println("Valid directory name detected : " + arg);

                            // TODO process directory in some way
                        }
                    }
                    else {
                        System.err.println("Specified file/directory name does not exist : " + arg);
                    }
                }

            }
        }
        else {
            System.out.println("No Arguments specified");
        }

    }

}