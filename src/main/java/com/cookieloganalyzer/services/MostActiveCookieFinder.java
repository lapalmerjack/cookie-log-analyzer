package com.cookieloganalyzer.services;

 import com.cookieloganalyzer.model.CookieLog;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The CookieService class is used to get the most active cookies from a list. So
 * far, the class only handles retrieving the most active cookies from a particular date, but can be
 * expanded to handle other operations in the future versions of the application.
 *
 * @author Maurice Skinner
 * @version 1.0
 * @since 2025-04-01
 */
public class MostActiveCookieFinder {

  private static final Logger LOGGER = LoggerFactory.getLogger(MostActiveCookieFinder.class);

    /**
     * Retrieves the most active cookies from a list of {@link CookieLog} objects.
     * This method processes a list of {@link CookieLog} entries and calculates the frequency of each unique cookie.
     * It then returns a list of cookies that appear with the highest frequency.
     *
     * @param cookieLogs The list of {@link CookieLog} objects representing individual cookie logs.
     *                   Each log contains information about a particular cookie and its usage.
     *
     * @return A list of {@link String} representing the cookies that have the highest frequency
     *         (i.e., the most active cookies).
     *
     *
     * @see CookieLog
     */
  public List<String> getMostActiveCookies(List<CookieLog> cookieLogs) {
      LOGGER.debug("adding most frequent cookie(s) to list");
    Map<String, Long> cookieFrequencyMap = cookieLogs.stream()
            .collect(Collectors.groupingBy(CookieLog::cookie, Collectors.counting()));

    long highestFrequency = cookieFrequencyMap.values().stream()
            .max(Long::compare)
            .orElse(0L);


    return cookieFrequencyMap.entrySet().stream()
            .filter(entry -> entry.getValue() == highestFrequency)
            .map(Map.Entry::getKey).collect(Collectors.toList());
  }




}
