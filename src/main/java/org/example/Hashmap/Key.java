package org.example.Hashmap;

import java.util.Objects;

public class Key<T extends Comparable<T>> implements Comparable<Key<T>>{
    private T key;

    public Key(T key) {
        this.key = key;
    }

    public T getValue() {
        return key;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key<?> key1 = (Key<?>) o;
        return Objects.equals(key, key1.key);
    }

    @Override
    public int hashCode () {
        return Objects.hash(key);
    }

    @Override
    public int compareTo (Key<T> o) {
        return this.key.compareTo(o.key);
    }
}
