package com.cookieloganalyzer.util;


import com.cookieloganalyzer.exceptions.CommandLineDateNotValidException;
import com.cookieloganalyzer.exceptions.DateDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Utility class for handling date parsing and validation. It provides methods to
 * parse a date string into a {@link LocalDate} object and validate command line
 * date input format.
 * This class provides static methods that can be used throughout the application
 * to handle date operations reliably and consistently.
 *
 * @since 2024-01-30
 * @version 1.0
 */
public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            DateUtils.class);

    /**
     * Parses a date string into a {@link LocalDate} object.
     * The method also checks if the parsed date is not in the future.
     *
     * @param date The date string to be parsed.
     * @return A {@link LocalDate} object representing the parsed date.
     * @throws DateTimeParseException if the date string is not in a valid format or
     *         cannot be parsed.
     * @throws DateDoesNotExistException if the date does not exist (e.g., February 29 on a non-leap year).
     */
    public static LocalDate parseDateStringToLocalDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            LocalDate parsedDate =  LocalDate.parse(date, formatter);

            throwAnErrorIfDateIsInTheFuture(parsedDate);

            return parsedDate;

        } catch (DateTimeParseException ex) {
            LOGGER.error("Local date parsing error occurred", ex);
            throw new DateTimeParseException("Failed to parse date: " + date, ex.getParsedString(),
                    ex.getErrorIndex());
        }
    }

    /**
     * Validates that the given date string matches the expected format for command line
     * input (yyyy-MM-dd).
     * <p>
     * If the date string does not match the expected format, a {@link CommandLineDateNotValidException}
     * is thrown.
     * </p>
     *
     * @param date The date string to be validated.
     * @throws CommandLineDateNotValidException if the date string does not match the expected format.
     */
    public static void throwErrorIfDateIsNotCorrectFormat(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(date).matches()) {
            LOGGER.error("Error: preparing command line date not valid exception");
            throw new CommandLineDateNotValidException(date);
        }
    }


    private static void throwAnErrorIfDateIsInTheFuture(LocalDate localDate) {
        if (localDate.isAfter(LocalDate.now())) {
            LOGGER.error("Error: preparing future date exception");
            throw new DateDoesNotExistException(localDate);
        }
    }
}
