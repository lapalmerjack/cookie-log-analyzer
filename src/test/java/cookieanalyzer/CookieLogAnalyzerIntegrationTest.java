package cookieanalyzer;

import com.cookieloganalyzer.api.LineParser;
import com.cookieloganalyzer.model.CookieLog;
import com.cookieloganalyzer.services.CookieLogParser;
import com.cookieloganalyzer.services.FileReader;
import com.cookieloganalyzer.services.MostActiveCookieFinder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CookieLogAnalyzerIntegrationTest {


    @Test
    void mainIntegrationTest() throws IOException {

        LineParser lineParser = new CookieLogParser();
        FileReader fileReader = new FileReader(lineParser);


        String testDate = "2018-12-08";
        String file = "src/test/resources/test_cookie_log.csv";


        List<CookieLog> cookieLogs = fileReader.retrieveCookieRecordsFromFile(file, testDate);


        assertTrue(cookieLogs.stream().anyMatch(cookieLog -> "fbcn5UAVanZf6UtG".equals(cookieLog.cookie())));
        assertTrue(cookieLogs.stream().anyMatch(cookieLog -> "4sMM2LxV07bPJzwf".equals(cookieLog.cookie())));
        assertTrue(cookieLogs.stream().anyMatch(cookieLog -> "SAZuXPGUrfbcn5UA".equals(cookieLog.cookie())));
        assertEquals(3, cookieLogs.size());


        MostActiveCookieFinder mostActiveCookieFinder = new MostActiveCookieFinder();
        List<String> mostActiveCookies = mostActiveCookieFinder.getMostActiveCookies(cookieLogs);


        assertTrue(mostActiveCookies.contains("SAZuXPGUrfbcn5UA"));
        assertTrue(mostActiveCookies.contains("4sMM2LxV07bPJzwf"));
        assertTrue(mostActiveCookies.contains("fbcn5UAVanZf6UtG"));

        assertEquals(3, cookieLogs.size());
    }
}