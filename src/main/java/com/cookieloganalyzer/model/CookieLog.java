package com.cookieloganalyzer.model;


import java.time.LocalDate;

/**
 * The CookieRecord represents an object of one logged entry within the cookie_log file. This record
 * enables the program to use the log information for further processing in the
 * OperationSelector class
 *
 * @param timestamp Represents the timestamp within the cookie_log file
 * @param cookie    Represents a cookie that is stored in the cookie_log file
 * @author Maurice Skinner
 * @version 1.0
 * @since 2025-04-01
 */
public record CookieLog(LocalDate timestamp, String cookie) {

}
