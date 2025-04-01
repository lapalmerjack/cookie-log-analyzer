package cookieanalyzer.services;


import com.cookieloganalyzer.api.LineParser;
import com.cookieloganalyzer.model.CookieLog;
import com.cookieloganalyzer.services.CookieLogParser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CookieLogParserTest {


    private final LineParser lineParser = new CookieLogParser();

    @Test
    public void shouldParseLineForValidInput() {
        String line = "k3VzQjG5Srfbcn5T,2018-12-08T23:30:00+00:00";

         LocalDate expectedDate = LocalDate.parse("2018-12-08");
        String expectedCookie = "k3VzQjG5Srfbcn5T";

         CookieLog result = lineParser.parseLine(line);

         assertNotNull(result, "Parsed result should not be null");
        assertEquals(expectedDate, result.timestamp());
        assertEquals(expectedCookie, result.cookie());
    }


    @Test
    public void shouldThrowAnErrorWithTooManyCommas() {

        String line = "cookie1,2021-12-31,extra";


        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> lineParser.parseLine(line));
        assertEquals("Line must contain exactly two comma-separated values: cookie and timestamp.", exception.getMessage());
    }


    @Test
    public void shouldNotParseLineIfCommandLineACommandLineArgumentIsNotValid() {

        String invalidLine = "k3VzQjG5Srfbcn5T,";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            lineParser.parseLine(invalidLine);
        });


        assertEquals(exception.getMessage(), "Line must contain exactly two comma-separated values: cookie and timestamp.");
    }




}
