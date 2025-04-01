package cookieanalyzer.utils;


import com.cookieloganalyzer.exceptions.CommandLineDateNotValidException;
import com.cookieloganalyzer.exceptions.DateDoesNotExistException;
import com.cookieloganalyzer.util.DateUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilsTest {

    @Test
    void shouldParseStringToALocalDate() {
        LocalDate localDateResult =  DateUtils.parseDateStringToLocalDate("2018-12-08T23:30:00+00:00");
        assertEquals("2018-12-08", localDateResult.toString());

    }

    @Test
    void shouldThrowAnErrorIfDateDoesNotExistWhileParsing() {
        assertThrows(DateTimeParseException.class, () -> DateUtils.parseDateStringToLocalDate("2021-02-29T23:30:00+00:00"));
    }

    @Test
    void shouldThrowAnErrorWhenProvidedDateIsInTheFuture() {
        assertThrows(DateDoesNotExistException.class, () -> DateUtils.parseDateStringToLocalDate("2027-02-25T23:30:00+00:00"));
    }


    @Test
    void showThrowAnErrorWhenTheDateStringIsNotActuallyADate() {
        assertThrows(DateTimeParseException.class, () -> DateUtils.parseDateStringToLocalDate("Awesome to the max"));
    }

    @Test
    void shouldThrowAnErrorWhenDateFromCommandLineIsNotValid() {
        assertThrows(CommandLineDateNotValidException.class, () -> DateUtils.throwErrorIfDateIsNotCorrectFormat("39-20-20T23:30:00+00:00"));

    }

}
