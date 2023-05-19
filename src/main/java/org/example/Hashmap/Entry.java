package org.example.Hashmap;


import java.util.Objects;

public class Entry <K, V>{
    private final K key;
    public V value;
    public Entry<K,V> next;



    public Entry(K key, V value){
        this.key = key;
        this.value = value;
        next = null;
    }
    @SuppressWarnings("unused")
    public Entry(K key, V value, Entry<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey() {
        return key;
    }




    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry<?, ?> entry = (Entry<?, ?>) o;
        return Objects.equals(key, entry.key) && Objects.equals(value, entry.value) && Objects.equals(next, entry.next);
    }

}
