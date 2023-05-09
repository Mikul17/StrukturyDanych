package org.example.Hashmap;

import java.util.LinkedList;

public class Hashmap<T1 extends Comparable<T1>, T2 extends Comparable<T2>>{
    //variables
    LinkedList<Entry>[] hashMap = new LinkedList[2];
    int size = 0;
    //constructor
    public Hashmap(T1 key, T2 value){
        Key<T1> newKey = new Key<>(key);
        Value<T2> newValue = new Value<>(value);
        put(newKey, newValue);
    }

    public Hashmap(){
        this.hashMap = new LinkedList[2];
        this.size = 0;
    }
    //put methods
    public void put(Key<T1> key, Value<T2> value){
        if(size >= hashMap.length){
            resize();
        }

        int index = Math.abs(getIndex(key)) % hashMap.length;

        if(hashMap[index] == null){
            hashMap[index] = new LinkedList<>();
        }else{
            //Hash collision handling
            for (Entry entry : hashMap[index]) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
            }
        }
        hashMap[index].add(new Entry(key, value));
        size++;
    }

    public void put(T1 key, T2 value){
        Key<T1> newKey = new Key<>(key);
        Value<T2> newValue = new Value<>(value);
        put(newKey, newValue);
    }
    //get methods
    public Value get(Key<T1> key){
        int index = getIndex(key) % hashMap.length;
        if(hashMap[index] == null){
            return null;
        }else{
            for (Entry entry : hashMap[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    //remove methods
    public void remove(Key<T1> key){
       if(key == null){
           return;
       }

       int index = getIndex(key) % hashMap.length;
       Entry toRemove = null;

       for(Entry entry : hashMap[index]){
           if(entry.getKey().equals(key)){
               toRemove = entry;
               break;
           }
       }

       if(toRemove == null){
           return;
       }

       hashMap[index].remove(toRemove);
    }

    //contains methods
    public boolean contains(Key<T1> key){
        if(key == null){
            return false;
        }

        int index = getIndex(key) % hashMap.length;
        if(hashMap[index] == null){
            return false;
        }else{
            for (Entry entry : hashMap[index]) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    //utility methods
    public int size(){
        return size;
    }

    public void resize(){
        LinkedList<Entry>[] oldHashMap = hashMap;
        hashMap = new LinkedList[oldHashMap.length * 2];
        size = 0; // reset size to 0 since we're re-adding all entries
        for (LinkedList<Entry> entries : oldHashMap) {
            if (entries != null) {
                for (Entry entry : entries) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public int getIndex(Key<T1> key){
        return key.hashCode();
    }
    public void print() {
        for (LinkedList<Entry> entries : hashMap) {
            if (entries != null) {
                for (Entry entry : entries) {
                    System.out.println(entry.getKey().getValue() + " " + entry.getValue().getValue());
                }
            }
        }
    }
}

