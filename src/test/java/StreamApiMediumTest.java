import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import stream.StreamApiMedium;

public class StreamApiMediumTest {
  private static final String NEW_LIST_REGEX = ".*newArrayList.*|.*newLinkedList.*";
  private static StreamApiMedium javaStreamApi;
  private static String resultCode;

  @BeforeClass
  public static void setUp() {
    javaStreamApi = new StreamApiMedium();
//    resultCode = Solution.getSolution().replaceAll("\\s", "");
  }

  @Test
  public void getCompanies_basicData() {
    Map<String, Integer> basicInput = Map.of(
      "Clarity", 2_000, "Oracle", 50_000,
      "MicroSystem", -2_000, "Odyssey", -100, "Jupiter", 200,
      "Infinity", 2_000_000, "Destiny", 0);
    List<String> expected = List.of("Clarity", "Infinity", "Jupiter", "Oracle");
    Assert.assertEquals(
      String.format("getCompanies: Incorrect result companies for input - %s.\n", basicInput),
      expected, javaStreamApi.getCompanies(basicInput));
  }

  @Test
  public void getCompanies_emptyData() {
    Map<String, Integer> basicInput = Collections.emptyMap();
    List<String> expected = Collections.emptyList();
    Assert.assertEquals(
      String.format("getCompanies: Incorrect result companies for input - %s.\n",
        basicInput),
      expected, javaStreamApi.getCompanies(basicInput));
  }

  @Test
  public void charsIncrementation_basicData() {
    String letters = "ABCD";
    String expectedOneShift = "BCDE";
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        letters, 1), expectedOneShift, javaStreamApi.charsIncrementation(letters, 1));
    String expectedTwoShifts = "CDEF";
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        letters, 2),
      expectedTwoShifts, javaStreamApi.charsIncrementation(letters, 2));
    String expectedFiveShifts = "FGHI";
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        letters, 5),
      expectedFiveShifts, javaStreamApi.charsIncrementation(letters, 5));
  }

  @Test
  public void charsIncrementation_numericAndPunctuationData() {
    String nonLetters = "123..{1}";
    String expectedTwoShifts = "34500}3";
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        nonLetters, 2),
      expectedTwoShifts, javaStreamApi.charsIncrementation(nonLetters, 2));
    String expectedFourShifts = "56722\u007F5\u0081";
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        nonLetters, 4),
      expectedFourShifts, javaStreamApi.charsIncrementation(nonLetters, 4));
  }

  @Test
  public void charsIncrementation_mixedData() {
    String mixedSymbols = "1_ABCD_1";
    String expectedThreeShifts = "4bDEFGb4";
    String expectedSevenShifts = "8fHIJKf8";
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        mixedSymbols, 3),
      expectedThreeShifts, javaStreamApi.charsIncrementation(mixedSymbols, 3));
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        mixedSymbols, 7),
      expectedSevenShifts, javaStreamApi.charsIncrementation(mixedSymbols, 7));
  }

  @Test
  public void charsIncrementation_emptyData() {
    String emptyInput = "";
    String expected = "";
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        emptyInput, 3),
      expected, javaStreamApi.charsIncrementation(emptyInput, 3));
    Assert.assertEquals(
      String.format("charsIncrementation: Incorrect result for input - %s and increment - %d.\n",
        emptyInput, 7),
      expected, javaStreamApi.charsIncrementation(emptyInput, 7));
  }

  @Test
  public void getOldestPersonAge_basicData() {
    List<String> basicInput = List.of("08:John", "119:Anthony", "019:Nicolas",
      "037:Brad", "056:Britney", "043:Annie", "019:Jorge", "001:Bruce");
    Long expected = 119L;
    Assert.assertEquals(
      String.format("getOldestPersonAge: Incorrect result for input %s", basicInput),
      expected, javaStreamApi.getOldestPersonAge(basicInput));
  }

  @Test
  public void getOldestPersonAge_biggerData() {
    List<String> basicInput = List.of("090:John", "-005:Anthony", "000:Nicolas",
      "037:Brad", "088:Britney", "007:Annie", "091:Jorge", "-120:Bruce",
      "022:Jasper", "001:Cortney", "050:Emily", "047:Naomi", "090:Leonard");
    Long expected = 91L;
    Assert.assertEquals(
      String.format("getOldestPersonAge: Incorrect result for input %s.\n", basicInput),
      expected, javaStreamApi.getOldestPersonAge(basicInput));
  }

  @Test
  public void getOldestPersonAge_invalidData() {
    List<String> invalidInput = List.of("Invalid input");
    Exception exception = new Exception();
    try {
      javaStreamApi.getOldestPersonAge(invalidInput);
    } catch (Exception e) {
      exception = e;
    }
    Assert.assertEquals(
      String.format("getOldestPersonAge: For input %s — instance of "
        + "RuntimeException should be thrown.\n", invalidInput),
      true, exception instanceof RuntimeException);
  }

  @Test
  public void getOldestPersonAge_emptyList() {
    List<String> emptyInput = Collections.emptyList();
    Exception exception = new Exception();
    try {
      javaStreamApi.getOldestPersonAge(emptyInput);
    } catch (Exception e) {
      exception = e;
    }
    Assert.assertEquals(
      String.format("getOldestPersonAge: For input %s — NoSuchElementException "
          + "should be thrown, but was %s.\n",
        emptyInput, exception),
      NoSuchElementException.class, exception.getClass());
  }

  @Test
  public void getVisitorsPerYear_uniqueData() {
    int year = 2018;
    List<String> basicRecords = List.of("John Stevenson:2020", "Bruce Cage:2015",
      "Britney Johns:2005", "Brad Wang:2005", "Lee Stone:2011",
      "Rickie Garrison:2015", "Taras Kozak:2017", "Bart Swan:2018", "Brad Wang:2005");
    Long expected = 1L;
    Assert.assertEquals(
      String.format("getVisitorsPerYear: Incorrect amount for year - %d and records - %s.\n",
        year, basicRecords),
      expected, javaStreamApi.getVisitorsPerYear(basicRecords, year));
  }

  @Test
  public void getVisitorsPerYear_basicData() {
    int year = 2015;
    List<String> basicRecords = List.of("John Stevenson:2020", "Bruce Cage:2015",
      "Britney Johns:2005", "Brad Wang:2005", "Lee Stone:2011",
      "Rickie Garrison:2015", "Taras Kozak:2017", "John Stevenson:2015",
      "Bart Swan:2018", "Brad Wang:2005", "John Stevenson:2015");
    Long expected = 3L;
    Assert.assertEquals(
      String.format("getVisitorsPerYear: Incorrect amount for year - %d and records - %s.\n",
        year, basicRecords),
      expected, javaStreamApi.getVisitorsPerYear(basicRecords, year));
  }

  @Test
  public void getVisitorsPerYear_emptyData() {
    int year = 2019;
    List<String> basicRecords = Collections.emptyList();
    Long expected = 0L;
    Assert.assertEquals(
      String.format("getVisitorsPerYear: Incorrect amount for year - %d and records - %s.\n",
        year, basicRecords),
      expected, javaStreamApi.getVisitorsPerYear(basicRecords, year));
  }


  @Test
  public void convertAndModifyNumbers_basicData() {
    List<Integer> positiveNumbers = List.of(1, 3, 5, 8);
    String expected = "(1)\n(11)\n(101)\n(1000)";
    Assert.assertEquals(
      String.format("convertAndModifyNumber: Incorrect result for input - %s.\n",
        positiveNumbers),
      expected, javaStreamApi.convertAndModifyNumbers(positiveNumbers));
  }

  @Test
  public void convertAndModifyNumbers_singleNumber() {
    List<Integer> singleNumber = List.of(1);
    String expected = "(1)";
    Assert.assertEquals(
      String.format("convertAndModifyNumber: Incorrect result for input - %s.\n",
        singleNumber),
      expected, javaStreamApi.convertAndModifyNumbers(singleNumber));
  }

  @Test
  public void convertAndModifyNumbers_complexData() {
    List<Integer> mixedNumbers = List.of(-533, 121, 44, 11, 27, 450);
    String expected = "(11111111111111111111110111101011)\n(1111001)\n(101100)\n"
      + "(1011)\n(11011)\n(111000010)";
    Assert.assertEquals(
      String.format("convertAndModifyNumber: Incorrect result for input - %s.\n",
        mixedNumbers),
      expected, javaStreamApi.convertAndModifyNumbers(mixedNumbers));
  }

//  @Test
//  public void solution_containsHardcoding() {
//    Assert.assertEquals("You should not create a List by yourself. Use Stream API methods.\n",
//      false, resultCode.matches(NEW_LIST_REGEX));
//  }
}
