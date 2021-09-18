package stream;


import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;


public class StreamApiMedium {
    /**
     * Given list strings representing records of patients' visits to a Hospital
     * {"John Stevenson:2020", "Andrew Ferguson:2012", "Andrew Ferguson:2013"}.
     * Return number of unique persons who have visited hospital during the given year (second input param).
     * Be careful, because one person may visit a hospital several times per year and for each visit new record
     * will be generated. Result shouldn't contain duplicates.
     */
    public Long getVisitorsPerYear(List<String> records, int year) {
        return records.stream()
                .map(e -> e.split(":"))
                .filter(a -> a[1].equals(Integer.toString(year)))
                .map(t -> t[0])
                .distinct()
                .count();
    }

    /**
     * Given a map with the following view : "company name" - "monthly income delta"  (String/Integer)
     * Return list of the companies with positive delta. Their names should be sorted alphabetically
     * Example input : {"Sun.ltd" : 20_000}, {"Micro" : -5_200}, {"Clarity": 0}, {"Odyssey": 9_640};
     * Output : {"Odyssey", "Sun.ltd"}
     */
    public List<String> getCompanies(Map<String, Integer> input) {
        return input.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .map(t -> t.getKey())
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Given a list of integer numbers, convert each integer into its' binary representation in string format
     * and join all of them into a single string and putting each value into brackets, it should look like this:
     * Input: {1, 20, 33}
     * Output:
     * (1)
     * (10100)
     * (100001)
     */
    public String convertAndModifyNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(v -> Integer.toBinaryString(v))
                .map(x -> new StringJoiner("", "(" + x.substring(0, x.length()), ")"))
                .map(c -> c.toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Given string value. Your task is
     * to increment char value of each symbol from the string. Amount to increment is
     * passed with the second input param - 'increment'
     */
    public String charsIncrementation(String string, int increment) {
        String collect = string.chars()
                .map(x -> x + increment)
                .mapToObj(i -> (char) i)
                .map(e -> e.toString())
                .collect(Collectors.joining());
        return collect;
    }

    /**
     * Given List of string where each element represents persons' age and name:
     * {"99:Johny", "20:Brad", ...} return the age of the oldest person
     */
    public Long getOldestPersonAge(List<String> people) {
        return people.stream()
                .map(e -> e.split(":"))
                .map(e -> Long.parseLong(e[0]))
                .mapToLong(x -> x)
                .max().getAsLong();
    }
}

