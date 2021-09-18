import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import stream.parson_cat.Cat;
import stream.parson_cat.Person;
import stream.parson_cat.StreamApiAdvanced;

public class StreamApiAdvancedTest {
  private static final String NEW_COLLECTION_REGEX =
    ".*newArrayList.*|.*newLinkedList.*|.*newHashMap.*";
  private static StreamApiAdvanced javaStreamApi;
  private static String resultCode;

  @BeforeClass
  public static void setUp() {
    javaStreamApi = new StreamApiAdvanced();
//    resultCode = Solution.getSolution().replaceAll("\\s", "");
  }

  @Test
  public void filterAndReverse_basicData() {
    int[] basicInput = new int[]{1, 4, 7, 10, 35, 97, 31, 55, 17, 74, 2, 10};
    List<Integer> expected = List.of(55, 35, 10, 10);
    Assert.assertEquals(
      String.format("Incorrect result list for the array - %s.\n",
        Arrays.toString(basicInput)),
      expected, javaStreamApi.filterAndReverse(basicInput));
  }

  @Test
  public void filterAndReverse_invalidData() {
    int[] basicInput = new int[]{1, 4, 7, 3, 97, 31, 56, 17, 74, 2, 9, 194, 22};
    List<Integer> expected = Collections.emptyList();
    Assert.assertEquals(
      String.format("filterAndReverse: Incorrect result list for the array - %s.\n",
        Arrays.toString(basicInput)),
      expected, javaStreamApi.filterAndReverse(basicInput));
  }

  @Test
  public void filterAndReverse_emptyData() {
    int[] basicInput = new int[0];
    List<Integer> expected = Collections.emptyList();
    Assert.assertEquals(
      String.format("filterAndReverse: Incorrect result list for the array - %s.\n",
        Arrays.toString(basicInput)),
      expected, javaStreamApi.filterAndReverse(basicInput));
  }

  @Test
  public void groupByAge_singleData() {
    Person becca = new Person("Becca", 25, Person.Sex.WOMAN, List.of(new Cat("Joe", 1)));
    List<Person> basicInput = List.of(becca);
    Map<Integer, List<Person>> expected = Map.of(25, List.of(becca));
    Assert.assertEquals(
      String.format("groupByAge: Incorrect result map of people for the input - %s.\n",
        basicInput),
      expected, javaStreamApi.groupByAge(basicInput));
  }

  @Test
  public void groupByAge_basicData() {
    List<Person> basicInput = initPeopleList();
    Person jess = new Person("Jess", 48, Person.Sex.WOMAN, initCatList());
    Person susy = new Person("Susy", 37, Person.Sex.WOMAN, List.of(new Cat("Kitty", 5)));
    Person hillary = new Person("Hillary", 48, Person.Sex.WOMAN, List.of(new Cat("Chris", 13)));
    Map<Integer, List<Person>> expected = Map.of(48, List.of(jess, hillary), 37, List.of(susy));
    Assert.assertEquals(
      String.format("groupByAge: Incorrect result map of people for the input - %s.\n",
        basicInput),
      expected, javaStreamApi.groupByAge(basicInput));
  }

  @Test
  public void groupWordsByLastChar_basicData() {
    List<String> basicInput = List.of("g", "gregory", "abcdg", "victory", "dance", "republic",
      "future", "compilation", "convention");
    Map<Character, List<String>> expected = Map.of('g', List.of("g", "abcdg"),
      'y', List.of("gregory", "victory"), 'e', List.of("dance", "future"), 'c',
      List.of("republic"), 'n', List.of("compilation", "convention"));
    Assert.assertEquals(
      String.format("groupWordsByLastChar: Incorrect result map of words for the input - %s.\n",
        basicInput),
      expected, javaStreamApi.groupWordsByLastChar(basicInput));
  }

  @Test
  public void groupWordsByLastChar_forbiddenSymbols() {
    List<String> basicInput = List.of("g23", "gregory", "abcdg", "11victory",
      "_christmas_", "republic16", "future!", "compilation", "convention", "exhibition", "fate");
    Map<Character, List<String>> expected = Map.of('g', List.of("abcdg"),
      'y', List.of("gregory", "11victory"), 'e', List.of("fate"),
      'n', List.of("compilation", "convention", "exhibition"));
    Assert.assertEquals(
      String.format("groupWordsByLastChar: Incorrect result map of words for the input - %s.\n",
        basicInput),
      expected, javaStreamApi.groupWordsByLastChar(basicInput));
  }

  @Test
  public void groupWordsByLastChar_emptyData() {
    List<String> basicInput = Collections.emptyList();
    Map<Character, List<String>> expected = Collections.emptyMap();
    Assert.assertEquals(
      String.format("groupWordsByLastChar: Incorrect result map of words for the input - %s.\n",
        basicInput),
      expected, javaStreamApi.groupWordsByLastChar(basicInput));
  }

  @Test
  public void solution_containsHardcoding() {
    Assert.assertEquals("You should not create a collection by yourself. "
        + "Use Stream API methods.\n",
      false, resultCode.matches(NEW_COLLECTION_REGEX));
  }

  private List<Cat> initCatList() {
    Cat tommy = new Cat("Tommy", 5);
    Cat snow = new Cat("Snow", 2);
    Cat jackie = new Cat("Jackie", 15);
    return List.of(tommy, snow, jackie);
  }

  private List<Person> initPeopleList() {
    List<Person> people = new ArrayList<>();
    Person jess = new Person("Jess", 48, Person.Sex.WOMAN, initCatList());
    people.add(jess);
    Person nick = new Person("Nick", 22, Person.Sex.MAN, initCatList());
    people.add(nick);
    Person joe = new Person("Joe", 25, Person.Sex.MAN, Collections.emptyList());
    people.add(joe);
    Person mary = new Person("Mary", 16, Person.Sex.WOMAN, initCatList());
    people.add(mary);
    Person susy = new Person("Susy", 37, Person.Sex.WOMAN, List.of(new Cat("Kitty", 5)));
    people.add(susy);
    Person barry = new Person("Barry", 29, Person.Sex.MAN, initCatList());
    people.add(barry);
    Person hillary = new Person("Hillary", 48, Person.Sex.WOMAN, List.of(new Cat("Chris", 13)));
    people.add(hillary);
    return people;
  }
}
