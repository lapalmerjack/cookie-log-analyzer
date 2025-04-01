package com.cookieloganalyzer.services;


import com.cookieloganalyzer.api.LineParser;
import com.cookieloganalyzer.model.CookieLog;
import com.cookieloganalyzer.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * A parser that converts a line of text into a {@link CookieLog} object.
 * This class implements the {@link LineParser} interface and provides a concrete implementation
 * for parsing a line of text that represents a cookie log entry. The line is expected to contain
 * a cookie identifier and a timestamp, separated by a comma. The timestamp is parsed into a {@link LocalDate}
 * object using {@link DateUtils#parseDateStringToLocalDate(String)}.
 */
public class CookieLogParser implements LineParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieLogParser.class);

    /**
     * Parses a line of text and converts it into a {@link CookieLog} object.
     * The method expects the line to contain exactly two comma-separated values: a cookie identifier
     * and a timestamp. If the line is not properly formatted (e.g., missing a cookie or timestamp, or
     * contains more than two values), an {@link IllegalArgumentException} is thrown.
     *
     * @param line The input string containing the cookie and timestamp information,
     *             formatted as "cookie,timestamp".
     * @return A {@link CookieLog} object containing the parsed cookie and timestamp.
     * @throws IllegalArgumentException If the line does not contain exactly two comma-separated values.
     * @see CookieLog
     * @see DateUtils#parseDateStringToLocalDate(String)
     */
    @Override
    public CookieLog parseLine(String line) {

        final String[] parts = line.split(",");

        if (parts.length != 2) {
            LOGGER.error("Trouble parsing line, preparing IllegalArgumentException");
            throw new IllegalArgumentException
                    ("Line must contain exactly two comma-separated values: cookie and timestamp.");
        }

        final String cookie = parts[0];
        LocalDate timeStamp = DateUtils.parseDateStringToLocalDate(parts[1]);

        return new CookieLog(timeStamp, cookie);
    }

}
