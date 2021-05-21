package de.bambussoft.meaningfulstrings;

import java.util.HashMap;

public class CountMap<K> extends HashMap<K, Long> {
    public long add(K key) {
        Long old = computeIfAbsent(key, k -> 0L);
        Long count = put(key, old + 1);
        assert count != null;
        return count;
    }
}
