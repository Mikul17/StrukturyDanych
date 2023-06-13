package org.example.LinkedHashMap;

import org.example.Hashmap.Entry;
import org.example.Hashmap.Hashmap;
import org.example.LinkedList;
import org.example.Map;
import org.example.SortingAlgorithm;

public class LinkedHashMap<K extends Comparable<K>, V extends Comparable<V>> implements Map<K, V> {
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
    public boolean containsKey(K key){
        return map.containsKey(key);
    }
    public void clear(){
        map.clear();
        entryList.clear();
    }

    public int getNumberOfHashCollisions(){
        return map.getNumberOfHashCollisions();
    }


    //Sorting algorithms
    public void sort(SortingAlgorithm algorithm, boolean sortByKey, boolean ascending){
        switch(algorithm){
            case quickSort:
                quicksort(0, entryList.size() - 1,sortByKey, ascending);
                break;
            case mergeSort:
                mergeSort(0, entryList.size() - 1, sortByKey, ascending);
                break;
            case shellSort:
                shellSort(sortByKey, ascending);
                break;
            case insertionSort:
                insertionSort(sortByKey, ascending);
                break;
            default:
                System.out.println("Invalid sorting algorithm");
        }
    }

    //quicksort
    public void quicksort(int low, int high, boolean sortByKey, boolean ascending){
        if(low < high){
            int pi = partition(low, high, sortByKey, ascending);
            quicksort(low, pi - 1, sortByKey, ascending);
            quicksort(pi + 1, high, sortByKey, ascending);
        }
    }

    public int partition(int low, int high, boolean sortByKey, boolean ascending){
        Entry<K, V> pivot = entryList.get(high);
        int i = low - 1;

        if(ascending){
            if(sortByKey){
                for(int j = low; j < high; j++){
                    if(entryList.get(j).getKey().compareTo(pivot.getKey()) < 0){
                        i++;
                        Entry<K, V> temp = entryList.get(i);
                        entryList.set(entryList.get(j), i);
                        entryList.set(temp, j);
                    }
                }
            }else{
                for(int j = low; j < high; j++){
                    if(entryList.get(j).value.compareTo(pivot.value) < 0){
                        i++;
                        Entry<K, V> temp = entryList.get(i);
                        entryList.set(entryList.get(j), i);
                        entryList.set(temp, j);
                    }
                }
            }
        }else{
            if(sortByKey){
                for(int j = low; j < high; j++){
                    if(entryList.get(j).getKey().compareTo(pivot.getKey()) > 0){
                        i++;
                        Entry<K, V> temp = entryList.get(i);
                        entryList.set(entryList.get(j), i);
                        entryList.set(temp, j);
                    }
                }
            }else{
                for(int j = low; j < high; j++){
                    if(entryList.get(j).value.compareTo(pivot.value) > 0){
                        i++;
                        Entry<K, V> temp = entryList.get(i);
                        entryList.set(entryList.get(j), i);
                        entryList.set(temp, j);
                    }
                }
            }
        }
        Entry<K, V> temp = entryList.get(i + 1);
        entryList.set(entryList.get(high), i + 1);
        entryList.set(temp, high);
        return i + 1;
    }

    //mergesort
    private void mergeSort(int low, int high, boolean sortByKey, boolean ascending){
            if(low < high){
                int mid = (low + high) / 2;
                mergeSort(low, mid, sortByKey, ascending);
                mergeSort(mid + 1, high, sortByKey, ascending);
                merge(low, mid, high, sortByKey, ascending);
            }
    }

    private void merge(int low, int mid, int high, boolean sortByKey, boolean ascending) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        Entry<K, V>[] L = new Entry[n1];
        Entry<K, V>[] R = new Entry[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = entryList.get(low + i);
        }
        for (int j = 0; j < n2; j++) {
            R[j] = entryList.get(mid + 1 + j);
        }
        int i = 0, j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (sortByKey) {
                if (ascending) {
                    if (L[i].getKey().compareTo(R[j].getKey()) <= 0) {
                        entryList.set(L[i],k);
                        i++;
                    } else {
                        entryList.set(R[j],k);
                        j++;
                    }
                } else {
                    if (L[i].getKey().compareTo(R[j].getKey()) >= 0) {
                        entryList.set(L[i],k);
                        i++;
                    } else {
                        entryList.set(R[j],k);
                        j++;
                    }
                }
            } else {
                if (ascending) {
                    if (L[i].value.compareTo(R[j].value) <= 0) {
                        entryList.set(L[i],k);
                        i++;
                    } else {
                        entryList.set(R[j],k);
                        j++;
                    }
                } else {
                    if (L[i].value.compareTo(R[j].value) >= 0) {
                        entryList.set(L[i],k);
                        i++;
                    } else {
                        entryList.set(R[j],k);
                        j++;
                    }
                }
            }
            k++;
        }
        while (i < n1) {
            entryList.set(L[i],k);
            i++;
            k++;
        }
        while (j < n2) {
            entryList.set(R[j],k);
            j++;
            k++;
        }
    }

    //shell-sort
    private void shellSort(boolean sortByKey, boolean ascending){
        int n = entryList.size();
        for(int gap = n/2; gap > 0; gap /= 2){
            for(int i = gap; i < n; i++){
                Entry<K, V> temp = entryList.get(i);
                int j;
                if(sortByKey){
                    if(ascending){
                        for(j = i; j >= gap && entryList.get(j - gap).getKey().compareTo(temp.getKey()) > 0; j -= gap){
                            entryList.set(entryList.get(j - gap), j);
                        }
                    }else{
                        for(j = i; j >= gap && entryList.get(j - gap).getKey().compareTo(temp.getKey()) < 0; j -= gap){
                            entryList.set(entryList.get(j - gap), j);
                        }
                    }
                }else{
                    if(ascending){
                        for(j = i; j >= gap && entryList.get(j - gap).value.compareTo(temp.value) > 0; j -= gap){
                            entryList.set(entryList.get(j - gap), j);
                        }
                    }else{
                        for(j = i; j >= gap && entryList.get(j - gap).value.compareTo(temp.value) < 0; j -= gap){
                            entryList.set(entryList.get(j - gap), j);
                        }
                    }
                }
                entryList.set(temp, j);
            }
        }
    }

    //insertion sort
    public void insertionSort(boolean sortByKey, boolean ascending){
        int n = entryList.size();
        for(int i = 1; i < n; ++i){
            Entry<K, V> key = entryList.get(i);
            int j = i - 1;
            if(sortByKey){
                if(ascending){
                    while(j >= 0 && entryList.get(j).getKey().compareTo(key.getKey()) > 0){
                        entryList.set(entryList.get(j), j + 1);
                        j = j - 1;
                    }
                }else{
                    while(j >= 0 && entryList.get(j).getKey().compareTo(key.getKey()) < 0){
                        entryList.set(entryList.get(j), j + 1);
                        j = j - 1;
                    }
                }
            }else{
                if(ascending){
                    while(j >= 0 && entryList.get(j).value.compareTo(key.value) > 0){
                        entryList.set(entryList.get(j), j + 1);
                        j = j - 1;
                    }
                }else{
                    while(j >= 0 && entryList.get(j).value.compareTo(key.value) < 0){
                        entryList.set(entryList.get(j), j + 1);
                        j = j - 1;
                    }
                }
            }
            entryList.set(key, j + 1);
        }
    }

    public boolean checkIfSorted(boolean sortByKey, boolean ascending){
        if(ascending){
            if(sortByKey){
                for(int i=0;i<entryList.size()-1;i++){
                    if(entryList.get(i).getKey().toString().compareTo(entryList.get(i+1).getKey().toString())>0){
                        return false;
                    }
                }
                return true;
            }
            for(int i=0;i<entryList.size()-1;i++){
                if(entryList.get(i).value.toString().compareTo(entryList.get(i+1).value.toString())>0){
                    return false;
                }
            }
        }else{
            if(sortByKey){
                for(int i=0;i<entryList.size()-1;i++){
                    if(entryList.get(i).getKey().toString().compareTo(entryList.get(i+1).getKey().toString())<0){
                        return false;
                    }
                }
                return true;
            }
            for(int i=0;i<entryList.size()-1;i++){
                if(entryList.get(i).value.toString().compareTo(entryList.get(i+1).value.toString())<0){
                    return false;
                }
            }
        }
        return true;
    }

    public Entry<K, V> getEntry (int index){
        return entryList.get(index);
    }
}
