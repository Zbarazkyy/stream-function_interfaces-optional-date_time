package java8_part1.find_auto_type;

import java.util.function.Function;

public class FindAutoType implements Function<Integer, AutoType> {
    @Override
    public AutoType apply(Integer integer) {
        return integer > 1500 ? AutoType.TRUCK : AutoType.CAR;
    }
}