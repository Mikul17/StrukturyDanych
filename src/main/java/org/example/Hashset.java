package org.example;

public class Hashset<T>{
    private T[] array;
    private int size;
    private int initialCapacity;

    @SuppressWarnings("unchecked")
    public Hashset(){
        this.size = 0;
        this.initialCapacity = 10;
        this.array = (T[]) new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public void add(T element){
        if(contains(element)){
            return;
        }
        if (size == initialCapacity){
            initialCapacity *= 2;
            T[] newArray = (T[]) new Object[initialCapacity];
            if (size >= 0) System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size] = element;
        size++;
    }

    public void remove(T element){
        for (int i = 0; i < size; i++){
            if (array[i].equals(element)){
                for (int j = i; j < size - 1; j++){
                    array[j] = array[j + 1];
                }
                size--;
                break;
            }
        }
    }

    public boolean contains(T element){
        for (T t : array){
          if(t != null && t.equals(element))
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void clear(){
        initialCapacity = 10;
        size = 0;
    }

    public void print(){
        for (T t : array)
            if(t != null)
                System.out.print(t + " ");
    }
}
