package java8_part1;

import java.util.function.BiFunction;

public class CompareEngineVolumes1 implements BiFunction<Integer, Integer, Integer> {

    @Override
    public Integer apply(Integer integer, Integer integer2) {
        return integer - integer2;
    }
}