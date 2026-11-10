package supporter;


import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class AttributeSupporter {

    public static <T extends Number> T defaultIfEmpty(T val, T defaultVal) {
        return val == null ? defaultVal : val;
    }

    public static <T extends Number, R> R defaultIfEmpty(Function<T, R> mapper, T val, R defaultVal) {
        return val == null ? defaultVal : mapper.apply(val);
    }

    public static <T extends CharSequence> T defaultIfEmpty(T val, T defaultVal) {
        return val == null || val.length() == 0 ? defaultVal : val;
    }

    public static <T extends CharSequence, R> R defaultIfEmpty(Function<T, R> mapper, T val, R defaultVal) {
        return val == null || val.length() == 0 ? defaultVal : mapper.apply(val);
    }

    public static <T extends Date> T defaultIfEmpty(T val, T defaultVal) {
        return val == null ? defaultVal : val;
    }

    public static <T extends Date, R> R defaultIfEmpty(Function<T, R> mapper, T val, R defaultVal) {
        return val == null ? defaultVal : mapper.apply(val);
    }

    public static <T, C extends Collection<T>> C defaultIfNull(C collections, Supplier<? extends C> supplier) {
        return collections == null ? Objects.requireNonNull(supplier).get() : collections;
    }

    public static <K, V> Map<K, V> defaultIfNull(Map<K, V> map, Supplier<? extends Map<K, V>> supplier) {
        return map == null ? Objects.requireNonNull(supplier).get() : map;
    }
}
