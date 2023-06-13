package org.example;

public interface Map<K extends Comparable<K>, V extends Comparable<V>> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    void display();

    private int compareKeys (K key1, K key2) {
        return 0;
    }

    private int compareValues (V value1, V value2) {
        return 0;
    }
}