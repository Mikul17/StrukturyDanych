package org.example;

public interface Map<K, V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    void display();
}