package aoc2024.common;

import java.util.Optional;

public class TypeUtils {

    /*
    public static <T, U extends T> Optional<U> safeCast(T obj) {
        if (obj instanceof U) {
            return Optional.of((U) obj);
        } else {
            return Optional.empty();
        }
    }
     */

    public static <T, U extends T> Optional<U> safeCast(T obj, Class<U> uClass) {
        if (uClass.isInstance(obj)) {
            return Optional.of(uClass.cast(obj));
        } else {
            return Optional.empty();
        }
    }
}
