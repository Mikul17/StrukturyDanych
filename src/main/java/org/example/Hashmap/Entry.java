package org.example.Hashmap;

public class Entry {
    private Key key;
    private Value value;

    public Entry (Key key, Value value) {
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
