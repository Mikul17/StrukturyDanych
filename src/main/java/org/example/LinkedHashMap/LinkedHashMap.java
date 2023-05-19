package org.example.LinkedHashMap;

import org.example.Hashmap.Entry;
import org.example.Hashmap.Hashmap;
import org.example.LinkedList;
import org.example.Map;

public class LinkedHashMap<K, V> implements Map<K, V> {
    private final int INITIAL_CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;
    private final LinkedList<Entry<K, V>> entryList;
    private final Hashmap<K, V> map;

    //Constructors
    public LinkedHashMap () {
        entryList = new LinkedList<>();
        map= new Hashmap<>(INITIAL_CAPACITY, LOAD_FACTOR);
    }
    public LinkedHashMap(int capacity){
        entryList = new LinkedList<>();
        map= new Hashmap<>(capacity, LOAD_FACTOR);
    }
    public LinkedHashMap(int capacity, float loadFactor){
        entryList = new LinkedList<>();
        map= new Hashmap<>(capacity, loadFactor);
    }
    public LinkedHashMap(float load_factor){
        entryList = new LinkedList<>();
        map= new Hashmap<>(INITIAL_CAPACITY, load_factor);
    }

    //Methods
    public void put(K key, V value){
        if(map.get(key) == null){
            Entry<K, V> entry = new Entry<>(key, value);
            entryList.addFirst(entry);
            map.put(key, value);
        }else{
            map.put(key, value);
        }

    }
    public void remove(K key){
        if(map.get(key) != null){
            Entry<K, V> entry = new Entry<>(key, map.get(key));
            map.remove(key);
            entryList.remove(entry);
        }
    }
    public V get(K key){
        return map.get(key);
    }

    //Utility methods
    public void display() {
        map.display();
    }
    public void displayEntryList(){
        System.out.println("EntryList");
        for(int i = 0; i < entryList.size(); i++){
            System.out.println(entryList.get(i).getKey() + " " + entryList.get(i).value);
        }
    }
    public int size(){
        return map.size();
    }
    @SuppressWarnings("unused")
    public int capacity(){
        return map.getCapacity();
    }
    public int entryListSize(){
        return entryList.size();
    }



    //Sorting algorithms
    public void sortByKey(){
        for(int i = 0; i < entryList.size(); i++){
            for(int j = 0; j < entryList.size() - 1; j++){
                if(entryList.get(j).getKey().toString().compareTo(entryList.get(j + 1).getKey().toString()) > 0){
                    Entry<K, V> temp = entryList.get(j);
                    entryList.set(entryList.get(j + 1),j);
                    entryList.set(temp, j + 1);
                }
            }
        }
    }
}
