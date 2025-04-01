package com.cookieloganalyzer.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.cookieloganalyzer.util.DateUtils;

/**
 * Represents a class based on what is received from the command line arguments. the JCommander
 * library aids in parsing the command line arguments and populating this class. The file field is
 * always required because without it, we are unable to load the basic data needed to process and
 * function much needed information. The class also provides a CookieRecord list, which is a base
 * for data that may be parsed in other methods throughout the application.
 *
 * @author Maurice Skinner
 * @version 1.0
 * @since 2025-04-01
 */
public class CommandLineOptions {

    @Parameter(names = "-f", description = "retrieving relevant csv file", required = true)
    private String fileName;

    @Parameter(names = "-d", description = "date to retrieve relevant cookies", required = true)
    private String date;

    /**
     * Gets the file name that is retrieved from the command line.
     *
     * @return The name of the file.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the date that is retrieved from the command line.
     *
     * @return The date that will be used for further processing
     */
    public String getDate() {
        return date;
    }

    /**
     * Parses command-line arguments into a CommandLineOptions object.
     *
     * @param args the command-line arguments
     */
    public void parseCommandLineOptions(String[] args) {
        try {
            JCommander.newBuilder()
                    .addObject(this)
                    .build()
                    .parse(args);
        } catch (ParameterException ex) {

            throw new ParameterException("Error parsing cli arguments: " + ex.getMessage());

        }

        DateUtils.throwErrorIfDateIsNotCorrectFormat(date);
    }

}
