package com.kang.utilman;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A Collection manipulation without any dependencies
 *
 * @author kang
 */
public class Collman {

    private Collman() {
    }

    public static int size(final Collection<?> collection) {
        return collection != null ? collection.size() : 0;
    }

    public static int size(final Map<?, ?> map) {
        return map != null ? map.size() : 0;
    }

    public static boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <T> Optional<T> get(final List<T> list, final int index) {
        if (index < 0 || size(list) <= index) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(list.get(index));
        }
    }

    public static <K, V> Optional<V> get(final Map<K, V> map, final K key) {
        if (isEmpty(map)) {
            return Optional.empty();
        }
        return Optional.ofNullable(map.get(key));
    }

    public static <T> boolean contains(final Collection<T> collection, final T search) {
        if (isEmpty(collection)) {
            return false;
        }
        return collection.contains(search);
    }

    public static <K, V> boolean containsKey(final Map<K, V> map, final K key) {
        if (isEmpty(map)) {
            return false;
        }
        return map.containsKey(key);
    }

    public static <K, V> boolean containsValue(final Map<K, V> map, final V value) {
        if (isEmpty(map)) {
            return false;
        }
        return map.containsValue(value);
    }

}
