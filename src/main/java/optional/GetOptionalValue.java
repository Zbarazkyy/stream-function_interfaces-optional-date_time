package optional;

import java.util.NoSuchElementException;
import java.util.Optional;

public class GetOptionalValue {
    public Integer getOptionalValue(int randomNumber) {
        Optional<Integer> optional = generateRandomOptional(randomNumber);

        return optional.orElseThrow(NoSuchElementException::new);

    }

    public Optional<Integer> generateRandomOptional(int randomNumber) {
        if (randomNumber % 2 != 0) {
            return Optional.of(randomNumber);
        }
        return Optional.empty();
    }
}