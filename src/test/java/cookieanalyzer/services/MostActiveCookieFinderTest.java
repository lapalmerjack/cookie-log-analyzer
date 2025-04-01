package cookieanalyzer.services;

import com.cookieloganalyzer.model.CookieLog;
import com.cookieloganalyzer.services.MostActiveCookieFinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MostActiveCookieFinderTest {


  @InjectMocks
  MostActiveCookieFinder mostActiveCookieFinder;



  @Test
  void shouldRetrieveAllCookiesOnDateIfMoreThanOneHasHighestNumber() {

    List<CookieLog> logs = List.of(
            new CookieLog(LocalDate.parse("2018-12-08"), "k3VzQjG5Srfbcn5T"),
            new CookieLog(LocalDate.parse("2018-12-08"), "a4fMM2LxF07pB9Zw"),
            new CookieLog(LocalDate.parse("2018-12-08"), "fbcn5UAV2n5f6UtG")
    );

    List<String> expectedCookieList = Arrays.asList("fbcn5UAV2n5f6UtG", "a4fMM2LxF07pB9Zw", "k3VzQjG5Srfbcn5T");

    List<String> resultList = mostActiveCookieFinder.getMostActiveCookies(logs);
    assertEquals(3, resultList.size());
    System.out.println(resultList);

    Collections.sort(expectedCookieList);
    Collections.sort(resultList);
    assertEquals(expectedCookieList, resultList);


  }

  @Test
  void shouldRetrieveOneCookieFromASpecificDateIfSaidCookieAppearsMostOnList() {
    List<CookieLog> logs = List.of(
            new CookieLog(LocalDate.parse("2018-12-09"), "r9T3laG8fkU1m9D2"),
            new CookieLog(LocalDate.parse("2018-12-09"), "y8ZuXlRmf9bcn6A9"),
            new CookieLog(LocalDate.parse("2018-12-09"), "8UAVnXqB7t2yP1V9"),
            new CookieLog(LocalDate.parse("2018-12-09"), "r9T3laG8fkU1m9D2")
    );

    String expectedCookie = "r9T3laG8fkU1m9D2";


    List<String> list = mostActiveCookieFinder.getMostActiveCookies(logs);
    assertFalse(list.contains("8UAVnXqB7t2yP1V9"));
    assertEquals(1, list.size());
    assertEquals(expectedCookie, list.get(0));

  }

  @Test
  void shouldReturnOneCookieIfThereIsOnlyOneCookieLogInTheList() {
    List<CookieLog> logs = List.of(
            new CookieLog(LocalDate.parse("2018-12-09"), "r9T3laG8fkU1m9D2")
    );

    List<String> list = mostActiveCookieFinder.getMostActiveCookies(logs);
    assertEquals(1, list.size());
    assertTrue(list.contains("r9T3laG8fkU1m9D2"));

  }

  @Test
  void shouldBeAnEmptyListIfAnEmptyCookieLogListIsPastToParameter() {
    List<CookieLog> emptyCookieLogList = Collections.emptyList();

    List<String> list = mostActiveCookieFinder.getMostActiveCookies(emptyCookieLogList);
    assertEquals(0, list.size());



  }

}