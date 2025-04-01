package com.cookieloganalyzer;

import com.cookieloganalyzer.api.LineParser;
import com.cookieloganalyzer.cli.CommandLineOptions;
import com.cookieloganalyzer.model.CookieLog;
import com.cookieloganalyzer.services.CookieLogParser;
import com.cookieloganalyzer.services.FileReader;
import com.cookieloganalyzer.services.MostActiveCookieFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
/**
 * The Main class serves as the entry point for the Cookie Log Analyzer application.
 * It handles command-line arguments, processes cookie logs from a specified file,
 * and outputs the most active cookies on a given date.
 *
 * This class orchestrates the flow by:
 * - Parsing the command-line arguments using CommandLineOptions
 * - Reading the cookie logs from a file using FileReader
 * - Determining the most active cookies using MostActiveCookieFinder
 * - Printing the most active cookies to the console
 *
 * It relies on the following components:
 * - CommandLineOptions to parse command-line options such as file name and date.
 * - CookieLogParser to parse individual cookie logs from each line in the file.
 * - FileReader to read the cookie log file and filter records based on the provided date.
 * - MostActiveCookieFinder to compute and return the most frequent cookies based on the parsed logs.
 *
 * @author Maurice Skinner
 * @version 1.0
 * @since 2024-01-30
 */
public class CookieLogAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieLogAnalyzer.class);

    public static void main(String[] args) throws IOException {

        LOGGER.info("Starting program");

        LOGGER.debug("parsing arguments");
        CommandLineOptions arguments = new CommandLineOptions();
        arguments.parseCommandLineOptions(args);

        LineParser lineParser = new CookieLogParser();
        FileReader fileReader = new FileReader(lineParser);


        List<CookieLog> cookieLogs = fileReader.retrieveCookieRecordsFromFile(arguments.getFileName(), arguments.getDate());

        MostActiveCookieFinder mostActiveCookieFinder = new MostActiveCookieFinder();
        List<String> mostActiveCookies = mostActiveCookieFinder.getMostActiveCookies(cookieLogs);

        if (mostActiveCookies.isEmpty()) {
            System.out.println("No active cookies found for date");
        } else {
            mostActiveCookies .forEach(System.out::println);
        }

        LOGGER.info("Shutting down program");

    }
}