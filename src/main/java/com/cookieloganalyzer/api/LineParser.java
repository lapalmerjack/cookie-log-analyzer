package com.cookieloganalyzer.api;

import com.cookieloganalyzer.model.CookieLog;
/**
 * Interface used to parse a line of input from the command line or a file into a {@link CookieLog} object.
 * Implementations of this interface should define how to interpret each line and convert it into a {@link CookieLog}.
 *
 * Example usage:
 *
 * LineParser lineParser = new CustomLineParser();
 * CookieLog cookieLog = lineParser.parseLine(line);
 *
 * @author Maurice Skinner
 * @version 1.0
 * @since 2025-04-01
 */
public interface LineParser {
    /**
     * Parses a single line of input and converts it into a {@link CookieLog} object.
     *
     * @param line the line of input to parse, typically from command line arguments or a file
     * @return a {@link CookieLog} object representing the parsed line
     * @throws IllegalArgumentException if the line is not in the expected format
     */
    CookieLog parseLine(String line);

}
