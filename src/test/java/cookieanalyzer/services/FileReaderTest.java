package cookieanalyzer.services;

import com.cookieloganalyzer.api.LineParser;
import com.cookieloganalyzer.model.CookieLog;
import com.cookieloganalyzer.services.CookieLogParser;
import com.cookieloganalyzer.services.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FileReaderTest {

    private FileReader fileReader;
    private LineParser lineParser;

    private static final String TEST_FILE_PATH = "src/test/resources/test_cookie_log_file_reader.csv";
    @BeforeEach
    public void setUp() throws IOException {
        lineParser = new CookieLogParser();  // Use an actual implementation of LineParser
        fileReader = new FileReader(lineParser);

    }

    @Test
    public void shouldRetrieveCookieRecordsFromValidFile() throws IOException {
        // Act: Retrieve the cookie logs for a specific date
        String date = "2018-12-09";
        List<CookieLog> result = fileReader.retrieveCookieRecordsFromFile(TEST_FILE_PATH, date);
        System.out.println(result);

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(cookieLog -> cookieLog.cookie().equals("AtY0laUfhglK3lC7")));
        assertTrue(result.stream().anyMatch(cookieLog -> cookieLog.timestamp().toString().equals("2018-12-09")));
    }

    @Test
    public void shouldBeAnEmptyListIfNoDatesMatchInputDateFromArguments() throws IOException {

        String date = "2024-01-03";
        List<CookieLog> result = fileReader.retrieveCookieRecordsFromFile(TEST_FILE_PATH, date);

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldThrowAnErrorIfTheFileDoesNotExist() {

        String nonExistentFile = "src/test/resources/non-existent-file.csv";
        IOException exception = assertThrows(IOException.class, () -> {
            fileReader.retrieveCookieRecordsFromFile(nonExistentFile, "2024-01-01");
        });

        assertEquals("Error reading file: src/test/resources/non-existent-file.csv", exception.getMessage());
    }

    @Test
    public void shouldReturnAnEmptyListIfTheFileIsAlsoEmpty() throws IOException {

        String emptyFilePath = "src/test/resources/test_cookie_log_file_reader_empty.csv";

        List<CookieLog> result = fileReader.retrieveCookieRecordsFromFile(emptyFilePath, "2024-01-01");


        assertTrue(result.isEmpty());
    }

}
