package cookieanalyzer.cli;

import com.beust.jcommander.ParameterException;
import com.cookieloganalyzer.cli.CommandLineOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CommandLineOptionsParseTest {


    public static final String GOOD_FILE = "test_cookie_log.csv";
    public static final String BAD_FILE = "bad_file.csv";

    public static final String DATE = "2018-12-09";

 private CommandLineOptions commandLineOptions;

 @BeforeEach
 public void setUp() {
  commandLineOptions = new CommandLineOptions();
 }



 @Test
    public void shouldCommandLineArgsWorkWhenValid() {
        String[] args = {"-f", GOOD_FILE, "-d", DATE};


       commandLineOptions.parseCommandLineOptions(args);


      assertEquals("test_cookie_log.csv", commandLineOptions.getFileName());
      assertEquals("2018-12-09", commandLineOptions.getDate());

    }

    @Test
    public void shouldNoFileErrorIsThrownWhenCorrectFileIsChosen() {
        String[] args = {"-f", GOOD_FILE, "-d", DATE};

        assertDoesNotThrow(() -> commandLineOptions.parseCommandLineOptions(args));


    }

    @Test
    public void shouldThrowAnErrorWhenFileIsNotSet() {

        String[] args = { "-d", DATE};

        Exception exception = assertThrows(ParameterException.class, () -> commandLineOptions.parseCommandLineOptions(args));
        assertThrows(ParameterException.class, () ->  commandLineOptions.parseCommandLineOptions(args));
        assertEquals("Error parsing cli arguments: The following option is required: [-f]", exception.getMessage());

    }


    @Test
    public void shouldThrowAnErrorWhenTHeDateIsNotSet() {

        String[] args = {"-f", BAD_FILE};

        Exception exception = assertThrows(ParameterException.class, () ->  commandLineOptions.parseCommandLineOptions(args));

       assertThrows(ParameterException.class, () ->  commandLineOptions.parseCommandLineOptions(args));
       assertEquals("Error parsing cli arguments: The following option is required: [-d]", exception.getMessage());
    }
}
