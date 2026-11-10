package supporter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MapSupporter {

    public static <V> V extract(Map<?, ?> map, Function<?, ?>... extractors) {
        return recursivelyExtract(map, extractors, 0);
    }

    public static <K, V> V extract(Map<?, ?> map, K... keys) {
        return recursivelyExtract(map, keys, 0);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <V> V recursivelyExtract(Object inDepth, Function[] extractors, int i) {
        if (inDepth == null || extractors == null || i == extractors.length) {
            return (V) inDepth;
        }
        return recursivelyExtract(extractors[i].apply(inDepth), extractors, i + 1);
    }

    @SuppressWarnings({"unchecked"})
    private static <V, K> V recursivelyExtract(Object inDepth, K[] keys, int i) {
        if (inDepth == null || keys == null || i == keys.length) {
            return (V) inDepth;
        }
        return recursivelyExtract(((Map<?, ?>) inDepth).get(keys[i]), keys, i + 1);
    }

    public static <K1, V> void inject(V value, Map<K1, V> map, K1 key1) {
        map.put(key1, value);
    }


    public static <K1, K2, V> void inject(V value, Map<K1, Map<K2, V>> map, K1 key1, K2 key2) {
        map.putIfAbsent(key1, new HashMap<>());
        inject(value, map.get(key1), key2);
    }

    public static <K1, K2, K3, V> void inject(V value, Map<K1, Map<K2, Map<K3, V>>> map, K1 key1, K2 key2, K3 key3) {
        map.putIfAbsent(key1, new HashMap<>());
        inject(value, map.get(key1), key2, key3);
    }

    public static <K1, K2, K3, K4, V> void inject(V value, Map<K1, Map<K2, Map<K3, Map<K4, V>>>> map, K1 key1, K2 key2, K3 key3, K4 key4) {
        map.putIfAbsent(key1, new HashMap<>());
        inject(value, map.get(key1), key2, key3, key4);
    }
}
