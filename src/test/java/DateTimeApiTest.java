import data_time.DateTimeApi;
import data_time.DateTimePart;
import org.junit.Assert;
import org.junit.Test;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class DateTimeApiTest {
  private DateTimeApi dateTimeApi = new DateTimeApi();
  private static final LocalDateTime LOCAL_DATE_TIME =
    LocalDateTime.of(2000, 12, 31, 23, 59, 59);

  @Test
  public void todayDate_FullOk() {
    String expected = String.valueOf(LocalDate.now());
    String result = dateTimeApi.todayDate(DateTimePart.FULL);
    Assert.assertEquals("Today date must be: " + expected
      + "\nbut was: " + result + "\n", expected, result);
  }

  @Test
  public void todayDate_YearOk() {
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    String expected = String.valueOf(year);
    String result = dateTimeApi.todayDate(DateTimePart.YEAR);
    Assert.assertEquals("Today date must be: " + expected
      + "\nbut was: " + result + "\n", expected, result);
  }

  @Test
  public void todayDate_MonthOk() {
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int monthIndex = calendar.get(Calendar.MONTH);
    String expected = Month.of(monthIndex + 1).toString();
    String result = dateTimeApi.todayDate(DateTimePart.MONTH);
    Assert.assertEquals("Today date must be: " + expected
      + "\nbut was: " + result + "\n", expected, result);
  }

  @Test
  public void todayDate_DayOk() {
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int numberOfDay = calendar.get(Calendar.DAY_OF_MONTH);
    String expected = String.valueOf(numberOfDay);
    String result = dateTimeApi.todayDate(DateTimePart.DAY);
    Assert.assertEquals("Today date must be: " + expected
      + "\nbut was: " + result + "\n", expected, result);
  }

  @Test(expected = DateTimeException.class)
  public void todayDate_ThrowException() {
    dateTimeApi.todayDate(DateTimePart.SECONDS);
    dateTimeApi.todayDate(DateTimePart.MINUTES);
  }

  @Test
  public void getDate_Ok() {
    Integer[] dateParams = {2018, 12, 21};
    Optional<LocalDate> expected =
      Optional.of(LocalDate.of(2018, 12, 21));
    Optional<LocalDate> result = dateTimeApi.getDate(dateParams);
    Assert.assertEquals("getData with params: " + Arrays.toString(dateParams)
        + "\nmust be: " + expected + "\n", expected, result);
  }

  @Test
  public void getDate_EmptyOk() {
    Integer[] dateParams = {};
    Optional<LocalDate> expected = Optional.empty();
    Optional<LocalDate> result = dateTimeApi.getDate(dateParams);
    Assert.assertEquals("getData with empty params must be empty optional\n", expected, result);
  }

  @Test
  public void addHours_Ok() {
    LocalTime expected = LocalTime.of(3, 59, 59);
    LocalTime result = dateTimeApi.addHours(LOCAL_DATE_TIME.toLocalTime(), 268);
    Assert.assertEquals("Result of addHours with time " + LOCAL_DATE_TIME.toLocalTime()
      + " and hoursToAdd 268 must be " + expected + "\n", expected, result);
  }

  @Test
  public void addMinutes_Ok() {
    LocalTime expected = LocalTime.of(9, 48, 59);
    LocalTime result = dateTimeApi.addMinutes(LOCAL_DATE_TIME.toLocalTime(), 589);
    Assert.assertEquals("Result of addMinutes with time " + LOCAL_DATE_TIME.toLocalTime()
      + " and minutesToAdd 589 must be " + expected + "\n", expected, result);
  }

  @Test
  public void addSeconds_Ok() {
    LocalTime expected = LocalTime.of(6, 39, 48);
    LocalTime result = dateTimeApi.addSeconds(LOCAL_DATE_TIME.toLocalTime(), 369589);
    Assert.assertEquals("Result of addSeconds with time " + LOCAL_DATE_TIME.toLocalTime()
      + " and secondsToAdd 369589 must be " + expected + "\n", expected, result);
  }

  @Test
  public void addWeeks_Ok() {
    LocalDate expected = LocalDate.of(2001, 1, 14);
    LocalDate result = dateTimeApi.addWeeks(LOCAL_DATE_TIME.toLocalDate(), 2);
    Assert.assertEquals("Result of addWeeks with date " + LOCAL_DATE_TIME.toLocalDate()
      + " and weeksToAdd 2 must be " + expected + "\n", expected, result);
  }

  @Test
  public void beforeDate_Ok() {
    LocalDate someDate = LocalDate.of(2019, 9, 3);
    String expected = someDate + " is before " + LocalDate.now();
    String result = dateTimeApi.beforeOrAfter(someDate);
    Assert.assertEquals("Expected: " + expected + "\nbut was: " + result + "\n", expected, result);
  }

  @Test
  public void afterDate_Ok() {
    LocalDate someDate = LocalDate.now().plusMonths(2);
    String expected = someDate + " is after " + LocalDate.now();
    String result = dateTimeApi.beforeOrAfter(someDate);
    Assert.assertEquals("Expected: " + expected + "\nbut was: " + result + "\n", expected, result);
  }

  @Test
  public void nowDate_Ok() {
    LocalDate someDate = LocalDate.now();
    String expected = someDate + " is today";
    String result = dateTimeApi.beforeOrAfter(someDate);
    Assert.assertEquals("Expected: " + expected + "\nbut was: " + result + "\n", expected, result);
  }

  @Test
  public void getDateInSpecificTimeZone_Ok() {
    LocalDateTime expectedResult1 = LocalDateTime.parse("2020-04-17T00:23:01");
    LocalDateTime actualResult1 = dateTimeApi
      .getDateInSpecificTimeZone("2020-04-16T15:23:01Z", "Asia/Tokyo");
    Assert.assertEquals("DateTime 2020-04-16T15:23:01Z in timezone Asia/Tokyo must be "
      + expectedResult1 + "\n", expectedResult1, actualResult1);

    LocalDateTime expectedResult2 = LocalDateTime.parse("2020-04-16T18:23:01");
    LocalDateTime actualResult2 = dateTimeApi
      .getDateInSpecificTimeZone("2020-04-16T15:23:01Z", "Europe/Athens");
    Assert.assertEquals("DateTime 2020-04-16T15:23:01Z in timezone Europe/Athens must be "
      + expectedResult2 + "\n", expectedResult2, actualResult2);
  }

  @Test
  public void offsetDateTime_UkraineOk() {
    LocalDateTime localDateTime =
      LocalDateTime.of(2019, Month.SEPTEMBER, 06, 13, 17);
    OffsetDateTime expected = OffsetDateTime.of(localDateTime, ZoneOffset.of("+02:00"));
    OffsetDateTime result = dateTimeApi.offsetDateTime(localDateTime);
    Assert.assertEquals("LocalDateTime " + localDateTime
        + " in Ukranian timezone must be " + expected + "\n", expected, result);
  }

  @Test
  public void parseDate_Ok() {
    LocalDate localDate = LocalDate.of(2019, 9, 21);
    Optional<LocalDate> expected = Optional.of(localDate);
    Optional<LocalDate> result = dateTimeApi.parseDate("20190921");
    Assert.assertEquals("Method parseDate must return optional of " + localDate
      + " for input 20190921\n", expected, result);
  }

  @Test
  public void parseDate_Incorrect() {
    Optional<LocalDate> expected = Optional.empty();
    Optional<LocalDate> result = dateTimeApi.parseDate("20193921");
    Assert.assertEquals("Method parseDate must return empty optional "
      + "for input 20193921\n", expected, result);
  }

  @Test
  public void customParseDate_Ok() {
    Optional<LocalDate> expected = Optional.of(LocalDate.of(2019, 9, 6));
    Optional<LocalDate> result = dateTimeApi.customParseDate("06 Sep 2019");
    Assert.assertEquals("Method customParseDate must return optional of " + expected.get()
      + " for input 06 Sep 2019\n", expected, result);
  }

  @Test
  public void customParseDate_Incorrect() {
    Optional<LocalDate> expected = Optional.empty();
    Optional<LocalDate> result = dateTimeApi.customParseDate("36 Sep 2019");
    Assert.assertEquals("Method customParseDate must return empty optional "
      + "for input 36 Sep 2019\n", expected, result);
  }

  @Test
  public void formatDate_Ok() {
    String expected = "06 September 2019 16:15";
    LocalDateTime localDateTime = LocalDateTime.of(2019, 9, 6, 16, 15, 26);
    String result = dateTimeApi.formatDate(localDateTime);
    Assert.assertEquals("LocalDateTime must be formatted as: " + expected
      + "\nbut was: " + result + "\n", expected, result);
  }
}
