package org.example.Hashmap;

import java.util.Objects;

public class Value {

    //variables
    private int value;

    //constructor
    public Value(int value){
        this.value = value;
    }

    //methods
    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return value == value1.value;
    }

}
