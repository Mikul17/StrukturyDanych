package org.example.Hashmap;

import java.util.LinkedList;

public class Hashmap{
    //variables
    LinkedList<Entry>[] hashMap = new LinkedList[2];
    int size = 0;
    //constructor
    public Hashmap(){}
    //put methods
    public void put(Key key, Value value){
        if(size >= hashMap.length){
            resize();
        }

        int index = getIndex(key) % hashMap.length;

        if(hashMap[index] == null){
            hashMap[index] = new LinkedList<>();
        }else{
            //Hash collision handling
            for (Entry entry : hashMap[index]) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    size++;
                    return;
                }
            }
        }
        hashMap[index].add(new Entry(key, value));
        size++;
    }
    //get methods
    public Value get(Key key){
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
    public void remove(Key key){
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
    public boolean contains(Key key){
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
        for (LinkedList<Entry> entries : oldHashMap) {
            if (entries != null) {
                for (Entry entry : entries) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public int getIndex(Key key){
        return key.hashCode();
    }
}
