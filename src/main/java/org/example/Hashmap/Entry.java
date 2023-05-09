package org.example.Hashmap;

public class Entry <T1 extends Comparable<T1>,T2 extends Comparable<T2>>{
    private final Key<T1> key;
    private final Value<T2> value;

    public Entry (Key<T1> key, Value<T2> value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue (Value value) {
    }
}
