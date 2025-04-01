package com.cookieloganalyzer.exceptions;

import java.time.LocalDate;

/**
 * Custom runtime exception that indicates that the provided date is in the future and can not be
 * used to verify the logs. Extends {@link RuntimeException}. Example:
 * {@code if (condition) { throw new DateDoesNotExistException("Description of the problem"); } }
 *
 * @author Maurice Skinner
 * @version 1.0
 * @since 2024-01-30
 */
public class DateDoesNotExistException extends RuntimeException {

    /**
     * This constructs a new {@code DateDoesNotExistException} when date has not yet passed.
     *
     * @param date the date used to help construct the detailed message
     */
    public DateDoesNotExistException(LocalDate date) {
        super("The date " + date + " does not yet exist.");
    }
}
