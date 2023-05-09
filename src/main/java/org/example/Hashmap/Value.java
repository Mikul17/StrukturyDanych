package org.example.Hashmap;


public class Value<T extends Comparable<T>> implements Comparable<Key<T>>{

    //variables
    private T value;

    //constructor
    public Value(T value){
        this.value = value;
    }

    //methods
    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return value == value1.value;
    }

    @Override
    public int compareTo (Key<T> o) {
        return this.value.compareTo(o.getValue());
    }
}
