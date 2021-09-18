package java8_part1.check;

import java.util.function.Predicate;

public class CheckElectricEngine implements Predicate<Engine> {
    @Override
    public boolean test(Engine engine) {
        return Engine.EngineType.ELECTRIC.equals(engine.getEngineType());
    }
}