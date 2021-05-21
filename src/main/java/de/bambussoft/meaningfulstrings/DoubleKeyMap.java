package de.bambussoft.meaningfulstrings;

import java.util.HashMap;

public class DoubleKeyMap<K1, K2, V> extends HashMap<K1, HashMap<K2, V>> {


    public void put(K1 k1, K2 k2, V value) {
        HashMap<K2, V> k2Map = computeIfAbsent(k1, k -> new HashMap<>());
        k2Map.put(k2, value);
    }

    public V get(K1 k1, K2 k2) {
        HashMap<K2, V> k2Map = get(k1);
        if (k2Map == null) {
            return null;
        } else {
            return k2Map.get(k2);
        }
    }

    public V computeIfAbsent(K1 k1, K2 k2, DoubleFunction<K1, K2, V> newValue) {
        V v = get(k1, k2);
        if (v == null) {
            v = newValue.apply(k1, k2);
            put(k1, k2, v);
        }
        return v;
    }


    public interface DoubleFunction<K1, K2, V> {
        V apply(K1 k1, K2 k2);
    }
}
