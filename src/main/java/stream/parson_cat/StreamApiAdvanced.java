package stream.parson_cat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApiAdvanced {
    /**
     * Given array of numbers, your task is to sort them in the reverse order and return only those
     * numbers that can be divided by 5 without a remainder.
     */
    public List<Integer> filterAndReverse(int[] inputNumbers) {
        return Arrays.stream(inputNumbers)
                .filter(e -> e % 5 == 0)
                .map(e -> Integer.parseInt(String.valueOf(e)))
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    /**
     * We want to gather some statistics: we have list of people and we want to know
     * distribution among the age of women who have cats and are older than 18
     * Result should have the following view: Map.of(19 - List.of(person1, person2, ...),
     * 21 - List.of(person3, person7, ...);
     */
    public Map<Integer, List<Person>> groupByAge(List<Person> people) {
        return people.stream()
                .filter(person -> person.getAge() > 18
                        && person.getSex().equals(Person.Sex.WOMAN)
                        && (!person.getCatList().isEmpty()))
                .collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(person -> person, Collectors.toList())));

    }

    /**
     * Given a list of random strings, group all of them by the last letter from the
     * string. If last char is a number or punctuation - skip the word.
     */
    public Map<Character, List<String>> groupWordsByLastChar(List<String> words) {

        return words.stream()
                .filter(e-> check(e))
                .collect(Collectors.groupingBy(word ->word.charAt(word.length()-1),Collectors.toList()));
    }
    private boolean check(String s) {
        return Character.isLetter(s.charAt(s.length() - 1));
    }
}
