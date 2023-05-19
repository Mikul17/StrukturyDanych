package org.example.Hashmap;


import org.example.Map;

public class Hashmap<K,V> implements Map<K,V> {
    //variables
    private Entry<K,V>[] hashMap;
    private float LOAD_FACTOR = 0.75f;
    private int size = 0;
    private int capacity;
    private int numberOfHashCollisions = 0;


    //constructors
    @SuppressWarnings({"unchecked","unused"})
    public Hashmap (int capacity) {
        this.capacity= capacity;
        this.hashMap = new Entry[capacity];
    }
    @SuppressWarnings("unchecked")
    public Hashmap(float loadFactor) {
        this.LOAD_FACTOR = loadFactor;
        this.capacity= 16;
        this.hashMap = new Entry[capacity];
    }
    @SuppressWarnings({"unchecked","unused"})
    public Hashmap() {
        this.capacity = 16;
        this.hashMap = new Entry[capacity];
    }
    @SuppressWarnings("unchecked")
    public Hashmap (int capacity, float loadFactor) {
        this.capacity = capacity;
        this.LOAD_FACTOR = loadFactor;
        this.hashMap = new Entry[capacity];
    }

    //methods

    public void put(K key, V value){
        if(key==null){
            throw new IllegalArgumentException("Key cannot be null");
        }

        Entry<K,V> newEntry = new Entry<>(key, value);
        int index = hashCode(key) % hashMap.length;

        if(hashMap[index] == null) {
            //If there is no entry under this index, create a new one with given key and value
            hashMap[index] = newEntry;
            size++;
            resizeIfNeeded();
        }else{
            Entry<K,V> entry = hashMap[index];
            boolean keyFound = false;
            while(entry.next != null){
                if(entry.getKey().equals(key)){
                    //If there already is an entry with this key, update the value
                    entry.value = value;
                    keyFound = true;
                    break;
                }
                entry = entry.next;
            }
            if(!keyFound && entry.getKey().equals(key)){
                //If the key matches the last entry in the linked list, update the value
                entry.value = value;
                keyFound = true;
            }
            if(!keyFound){
                //If there is no entry with this key, add a new entry to the end of the linked list
                entry.next=newEntry;
                numberOfHashCollisions++;
                size++;
            }
            resizeIfNeeded();
        }

    }
    public V get(K key) {
        if(key==null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        int index = hashCode(key) % hashMap.length;

        // Search for the Entry with the given key in the linked list at this index
        Entry<K, V> entry = hashMap[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                // If the key matches, return the value
                return entry.value;
            }
            entry = entry.next;
        }

        // If the key was not found, return null
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        Entry<K, V>[] resizedHashmap = new Entry[capacity];

        //Rewrite the old hashmap to resized one
        for (Entry<K, V> oldEntry : hashMap) {
            while (oldEntry != null) {
                int newIndex = hashCode(oldEntry.getKey()) % resizedHashmap.length;
                Entry<K, V> newEntry = new Entry<>(oldEntry.getKey(), oldEntry.value);

                if (resizedHashmap[newIndex] == null) {
                    resizedHashmap[newIndex] = newEntry;
                } else {
                    Entry<K, V> newLastEntry = resizedHashmap[newIndex];
                    while (newLastEntry.next != null) {
                        newLastEntry = newLastEntry.next;
                    }
                    newLastEntry.next = newEntry;
                }
                oldEntry = oldEntry.next;
            }
        }
        hashMap = resizedHashmap;
    }
    private void resizeDown(){
        while((float) size /capacity < LOAD_FACTOR){
            capacity /= 2;
        }
        resize();
    }
    public void remove(K key){
        if(key==null){
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hashCode(key) % hashMap.length;

        Entry<K, V> entry = hashMap[index];
        Entry<K, V> previousEntry = null;
        while (entry != null) {
            // If the key matches, remove the Entry from the linked list
            if (entry.getKey().equals(key)) {
                //If there is only one entry in the linked list, set the index to null
                if (previousEntry == null) {
                    hashMap[index] = entry.next;
                } else {
                    //If there are more entries, set the previous entry's next to the next entry
                    previousEntry.next = entry.next;
                }
                size--;
                break;
            }
            previousEntry = entry;
            entry = entry.next;
        }

        //adjust map capacity if needed
        if((float) size /capacity < LOAD_FACTOR){
            resizeDown();
        }
    }

    //utility methods
    public void display(){
        for (Entry<K, V> kvEntry : hashMap) {
            Entry<K, V> entry = kvEntry;
            while (entry != null) {
                System.out.println(entry.getKey() + " " + entry.value);
                entry = entry.next;
            }
        }
    }
    private void resizeIfNeeded (){
        //Resize the hashmap if the load factor is exceeded
        if((float) size /capacity > LOAD_FACTOR){
            resize();
        }
    }
    public int size(){
        return size;
    }
    public int getCapacity(){
        return capacity;
    }
    public int getNumberOfHashCollisions(){
        return numberOfHashCollisions;
    }

    //Hash functions
//    public int hashCode(K key){
//       return Math.abs(key.hashCode());
//    }

    public int hashCode(K key) {
        final int prime = 16777619;
        int hash = 2147483647;

        byte[] keyBytes = key.toString().getBytes();
        for (byte b : keyBytes) {
            hash ^= b;
            hash *= prime;
        }
        return Math.abs(hash);
    }

//    public int hashCode (K key) {
//        int hash;
//        return key == null ? 0 : Math.abs((hash = key.hashCode()) ^ (hash >>> 16));
//    }
}

