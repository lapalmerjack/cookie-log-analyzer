package com.cookieloganalyzer.exceptions;

/**
 * Custom runtime exception that indicates the provided date format is invalid. Extends {@link RuntimeException}.
 * Example:
 * {@code if (!isValidDate(date)) { throw new CommandLineDateNotValidException(date); } }
 *
 * @author Maurice Skinner
 * @version 1.0
 * @since 2025-04-01
 */
public class CommandLineDateNotValidException extends  RuntimeException{
    /**
     * This constructs a new {@code CommandLineDateNotValidException} with a message indicating that the provided
     * date format is invalid.
     *
     * @param date the invalid date format used to help construct the detailed message
     */
    public CommandLineDateNotValidException(String date) {
        super("Invalid date format. Expected format: yyyy-mm-dd, but got: " + date);
    }
}
