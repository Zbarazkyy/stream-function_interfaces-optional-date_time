import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import stream.StreamApi;

public final class StreamApiTest {
  private static final String NEW_LIST_REGEX = ".*newArrayList.*|.*newLinkedList.*";
  private static List<String> elements;
  private static StreamApi javaStreamApi;
  private static String resultCode;

  @BeforeClass
  public static void setUp() {
    javaStreamApi = new StreamApi();
    elements = Arrays.asList("a1", "a2", "a3", "a1");
//    resultCode = Solution.getSolution().replaceAll("\\s", "");
  }

  @Test
  public void getOddNumsSum_ok() {
    Integer expected = 9;
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    Integer result = javaStreamApi.getOddNumsSum(numbers);
    Assert.assertEquals(
      String.format("getOddNumsSum: The result should be %d for input data %s , but was %d.\n",
        expected, numbers, result),
      expected, result);
  }

  @Test
  public void getOddNumsSum_singleArray_ok() {
    Integer expected = 9;
    List<Integer> input = Collections.singletonList(9);
    Integer result = javaStreamApi.getOddNumsSum(input);
    Assert.assertEquals(
      String.format("getOddNumsSum: The result should be %d for input data %s , but was %d.\n",
        expected, input, result),
      expected, result);
  }

  @Test
  public void getOddNumsSum_zeroOdds() {
    Integer expected = 0;
    List<Integer> input = Arrays.asList(6, 2, 8, 4, 12);
    Integer result = javaStreamApi.getOddNumsSum(input);
    Assert.assertEquals(
      String.format("getOddNumsSum: The result should be %d for input data %s , but was %d.\n",
        expected, input, result),
      expected, result);
  }

  @Test
  public void calculateOccurrences_ok() {
    Long expected = 2L;
    String element = "a1";
    Long result = javaStreamApi.calculateOccurrences(elements, element);
    Assert.assertEquals(
      String.format("calculateOccurrences: "
          + "The result should be %d for elements %s and element %s, but was %d.\n",
        expected, elements, element, result),
      expected, result);
  }

  @Test
  public void calculateOccurrences_zeroOccurrences() {
    Long expected = 0L;
    String element = "a4";
    Long result = javaStreamApi.calculateOccurrences(elements, element);
    Assert.assertEquals(
      String.format("calculateOccurrences: "
         + "The result should be %d for elements %s and element %s, but was %d.\n",
        expected, elements, element, result),
      expected, result);
  }

  @Test
  public void getFirstElement_ok() {
    Optional<String> expected = Optional.of("a1");
    Optional<String> result = javaStreamApi.getFirstElement(elements);
    Assert.assertEquals(
      String.format("getFirstElement: The result should be %s for elements %s but was %s.\n",
        expected, elements, result),
      expected, result);
  }

  @Test
  public void getFirstElement_emptyList() {
    Optional<String> expected = Optional.empty();
    List<String> emptyElements = Collections.emptyList();
    Optional<String> result = javaStreamApi.getFirstElement(emptyElements);
    Assert.assertEquals(
      String.format("getFirstElement: The result should be %s for elements %s but was %s.\n",
        expected, emptyElements, result),
      expected, result);
  }

  @Test
  public void findElement_ok() {
    String expected = "a3";
    String result = javaStreamApi.findElement(elements, expected);
    Assert.assertEquals(
      String.format("findElement: The result should be %s for elements %s and element %s, but was %s.\n",
        expected, elements, expected, result),
      expected, result);
  }

  @Test
  public void findElement_notOk() {
    String expected = "a4";
    Exception exception = new RuntimeException();
    try {
      javaStreamApi.findElement(elements, expected);
    } catch (Exception e) {
      exception = e;
    }
    Assert.assertEquals(
      String.format("findElement: For elements %s and element %s — %s should be thrown but was %s.\n",
        elements, expected, NoSuchElementException.class, exception.getClass()),
      NoSuchElementException.class, exception.getClass());
  }

  @Test
  public void getThreeSmallestNumbers_ok() {
    int[] basicArr = {5, 135, 3, 3, 0, 7, 23, 7, 15, -2, 44, 9};
    List<Integer> expected = List.of(-2, 0, 3);
    List<Integer> result = javaStreamApi.getThreeSmallestNumbers(basicArr);
    Assert.assertEquals(
      String.format("For array %s the result should be %s but was %s.\n",
        Arrays.toString(basicArr), expected, result),
      expected, result);
  }

  @Test
  public void getThreeSmallestNumbers_emptyArray() {
    int[] emptyArr = new int[0];
    List<Integer> expected = Collections.emptyList();
    List<Integer> result = javaStreamApi.getThreeSmallestNumbers(emptyArr);
    Assert.assertEquals(
      String.format("getThreeSmallestNumbers: For array %s the result should be %s but was %s.\n",
        Arrays.toString(emptyArr), expected, result),
      expected, result);
  }

  @Test
  public void getThreeSmallestNumbers_singleElement() {
    int[] singleElementArr = {14};
    List<Integer> expected = List.of(14);
    List<Integer> result = javaStreamApi.getThreeSmallestNumbers(singleElementArr);
    Assert.assertEquals(
      String.format("getThreeSmallestNumbers: For array %s the result should be %s but was %s.\n",
        Arrays.toString(singleElementArr), expected, result),
      expected, result);
  }

  @Test
  public void solution_containsHardcoding() {
    Assert.assertEquals("You should not create a List by yourself. Use Stream API methods.\n",
      false, resultCode.matches(NEW_LIST_REGEX));
  }
}
