package supporter;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IterableSupporter {

    @SafeVarargs
    public static <T> Collection<T> filterCollection(Collection<T> collections, Predicate<? super T>... filters) {
        return filterStream(collections, filters).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> Stream<T> filterStream(Collection<T> collections, Predicate<? super T>... filters) {
        Stream<T> stream = collections.stream();
        if (filters == null) {
            return stream;
        }
        for (Predicate<? super T> predicate : filters) {
            stream = stream.filter(predicate);
        }
        return stream;
    }

    @SafeVarargs
    public static <T> Stream<T> sortedStream(Stream<T> stream, Comparator<T>... comparators) {
        if (comparators == null || comparators.length == 0) {
            return stream;
        }
        Comparator<T> chainedComparator = comparators[0];
        for (int i = 1; i < comparators.length; i++) {
            Comparator<T> comparator = comparators[i];
            chainedComparator = chainedComparator.thenComparing(comparator);
        }
        return stream.sorted(chainedComparator);
    }

    @SafeVarargs
    public static <T> Collection<T> sortedCollection(Collection<T> collection, Comparator<T>... comparators) {
        return sortedStream(collection.stream(), comparators).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> Collection<T> mergeCollection(Collection<T>... collections) {
        return Arrays.stream(collections).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T, C extends Collection<T>> C removeInCollection(Collection<T> collections,
                                                                    Supplier<C> collectionFactory,
                                                                    Predicate<? super T>... conditions) {

        if (collections == null) {
            return null;
        }
        // 1. Supplier를 통해 원하는 타입의 새 컬렉션 생성 (Comparator 등 설정 유지)
        C resultCollection = collectionFactory.get();
        resultCollection.addAll(collections);
        // 2. 제거 조건이 없으면 복사본만 반환
        if (conditions == null || conditions.length == 0) {
            return resultCollection;
        }
        // 3. 조건 결합 (OR 연산)
        Predicate<T> combinedFilter = conditions[0]::test;
        for (int i = 1; i < conditions.length; i++) {
            combinedFilter = combinedFilter.or(conditions[i]);
        }
        // 4. 조건에 일치하는 요소 제거 후 반환
        resultCollection.removeIf(combinedFilter);
        return resultCollection;
    }


    public static <T, K1, K2, K3, R> Map<K1, Map<K2, Map<K3, R>>> groupAndCollect(Collection<T> collection,
                                                                                  Function<T, K1> key1Extractor,
                                                                                  Function<T, K2> key2Extractor,
                                                                                  Function<T, K3> key3Extractor,
                                                                                  Collector<T, ?, R> collector) {
        return collection.stream().collect(Collectors.groupingBy(
                key1Extractor, Collectors.groupingBy(key2Extractor, Collectors.groupingBy(key3Extractor, collector))
        ));
    }


    public static <T, K1, K2, R> Map<K1, Map<K2, R>> groupAndCollect(Collection<T> collection,
                                                                     Function<T, K1> key1Extractor,
                                                                     Function<T, K2> key2Extractor,
                                                                     Collector<T, ?, R> collector) {
        return collection.stream().collect(Collectors.groupingBy(
                key1Extractor, Collectors.groupingBy(key2Extractor, collector)
        ));
    }

    public static <T, K, R> Map<K, R> groupAndCollect(Collection<T> collection,
                                                      Function<? super T, ? extends K> keyExtractor,
                                                      Collector<? super T, ?, R> collector) {
        return collection.stream().collect(Collectors.groupingBy(keyExtractor, collector));
    }

    public static <T, K, R> Map<K, R> collectToMap(Collection<T> collection,
                                                   Function<? super T, ? extends K> keyExtractor,
                                                   Function<? super T, ? extends R> valueMapper) {
        return collection.stream().collect(Collectors.toMap(keyExtractor, valueMapper));
    }

    public static <T, K, R> Map<K, R> collectToMap(Collection<T> collection,
                                                   Function<? super T, ? extends K> keyExtractor,
                                                   Function<? super T, ? extends R> valueMapper,
                                                   BinaryOperator<R> mergeFunction) {
        return collection.stream().collect(Collectors.toMap(keyExtractor, valueMapper, mergeFunction));
    }

    public static <V, C extends Collection<V>> C flattenToCollection(Map<?, ?> map, Supplier<C> supplier) {
        C result = supplier.get();
        if (map == null || map.isEmpty()) {
            return result;
        }
        flatten(map, result);
        return result;
    }

    @SuppressWarnings({"unchecked"})
    private static <V> void flatten(Map<?, ?> map, Collection<V> result) {
        for (Object value : map.values()) {
            if (value instanceof Map) {
                flatten((Map<?, ?>) value, result);
            } else {
                result.add((V) value);
            }
        }
    }

    public static <T, V> Collection<V> remapCollection(Collection<T> collections, Function<T, V> mapper) {
        return collections.stream().map(mapper).collect(Collectors.toList());
    }

}
