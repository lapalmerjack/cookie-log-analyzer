package com.cookieloganalyzer.services;


import com.cookieloganalyzer.api.LineParser;
import com.cookieloganalyzer.model.CookieLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The FileReader class is used to read a file in order to populate a CookieLog list with dates
 * and their related cookies.
 *
 * @author Maurice Skinner
 * @version 1.0
 * @since 2025-04-01
 */
public class FileReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

  private final LineParser lineParser;

    public FileReader(LineParser lineParser) {
        this.lineParser = lineParser;
    }

  /**
   * This is a method which reads a file and then initializes a CookieRecord list
   *
   * @return The CookieRecord list used for further functionality
   */
  public List<CookieLog> retrieveCookieRecordsFromFile(String fileName, String date) throws IOException {

    LOGGER.debug("Reading lines from file");
    try (Stream<String> lines = Files.lines(Paths.get(fileName))) {

      return lines
              .filter(line -> !line.trim().isEmpty())
              .map(lineParser::parseLine)
              .filter(cookieLog -> date.equals(cookieLog.timestamp().toString()))
              .collect(Collectors.toList());

    } catch (IOException ex) {
      LOGGER.error("Error: preparing IOException");
      throw new IOException("Error reading file: " + ex.getMessage());
    }
  }

}
