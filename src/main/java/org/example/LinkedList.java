package org.example;

public class LinkedList <T>{
    private class Node{
        private T data;
        private Node next;
        private Node prev;

        public Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    private Node head;
    private Node tail;
    private int size;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //Adding elements to the list
    public void addFirst(T element){
        Node node = new Node(element);
        if (head == null){
            head = node;
            tail = node;
        }else{
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }
    public void addLast(T element){
        Node node = new Node(element);
        if (head == null){
            head = node;
            tail = node;
        }else{
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }
    public void add(T element, int index){
        if (index < 0 || index > size){
            return;
        }
        if (index == 0){
            addFirst(element);
        }else if (index == size){
            addLast(element);
        }else{
            Node node = new Node(element);
            Node current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            node.next = current;
            node.prev = current.prev;
            current.prev.next = node;
            current.prev = node;
            size++;
        }
    }

    public void addAll(LinkedList<T> list, boolean ascending){
        int size = list.size();
        if(ascending){
             for(int i = 0; i < size; i++){
                 addLast(list.getFirst());
                list.removeFirst();
                }
        }
        else{
            for(int i = 0; i < size; i++){
                addLast(list.getLast());
               list.removeLast();
               }   
        }
    }

    //Getting elements from the list
    public T getFirst(){
        if (head == null){
            return null;
        }
        return head.data;
    }
    public T getLast(){
        if (tail == null){
            return null;
        }
        return tail.data;
    }
    public T get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        Node current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.data;
    }

    public T getT(int index){
        if (index < 0 || index >= size){
            return null;
        }
        Node current = tail;
        for (int i = 0; i < index; i++){
            current = current.prev;
        }
        return current.data;
    }

    public T get(T element){
        Node current = head;
        while (current != null){
            if (current.data.equals(element)){
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    //Removing elements from the list

    public void removeFirst(){
        if (head == null){
        }
        if (head == tail){
            head = null;
            tail = null;
        }else{
            head = head.next;
            head.prev = null;
        }
        size--;
    }
    public void removeLast(){
        if (head == null){
        }
        if (head == tail){
            head = null;
            tail = null;
        }else{
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }
    public void remove(T element){
        Node current = head;
        while (current != null){
            if (current.data.equals(element)){
                if (current == head){
                    removeFirst();
                }else if (current == tail){
                    removeLast();
                }else{
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return;
            }
            current = current.next;
        }
    }
    public void remove(int index){
        if (index < 0 || index >= size){
            return;
        }
        Node current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        if (current == head){
            removeFirst();
        }else if (current == tail){
            removeLast();
        }else{
            current.prev.next = current.next;
            current.next.prev = current.prev;
            size--;
        }
    }

    //Utility methods
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }
    public void print(){
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null){
            sb.append(current.data).append(" ");
            current = current.next;
        }
        System.out.println(sb);
    }

    public void set (T element, int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = element;
    }

     //Sorting algorithms
     public void sort(SortingAlgorithm algorithm,LinkedList<T> list, boolean ascending){
        switch(algorithm){
            case quickSort:
                quicksort(list, ascending);
                break;
            case mergeSort:
                mergeSort(list, ascending); //ascending);
                break;
            case shellSort:
                shellSort(list, ascending);
                break;
            case insertionSort:
                insertionSort(list, ascending);
                break;
            default:
                System.out.println("Invalid sorting algorithm");
        }
    }


    //quicksort
    private void quicksort(LinkedList<T> list, boolean ascending) {
        if (list == null ||list.size() <= 1) {
            return;
        }
        
        // Choose the pivot element (last element in the list)
        T pivot = list.getLast();
        list.removeLast();
        
        // Create two new linked lists to hold elements smaller and greater than the pivot
        LinkedList<T> smallerList = new LinkedList<>();
        LinkedList<T> greaterList = new LinkedList<>();
        
        // Iterate through the list and partition elements based on the pivot
        while (!list.isEmpty()) {
            T current = list.getFirst();
            list.removeFirst();
            if(ascending){
            if (current.toString().compareTo(pivot.toString()) < 0) {
                smallerList.addLast(current);
            } else {
                greaterList.addLast(current);
            }
        }
        else{
            if (current.toString().compareTo(pivot.toString()) > 0) {
                smallerList.addLast(current);
            } else {
                greaterList.addLast(current);
            }
        }
        }
        
        // Recursively sort the smaller and greater lists
        if(checkIfSorted(smallerList)==false){
        quicksort(smallerList, ascending);}
        if(checkIfSorted(greaterList)==false){
        quicksort(greaterList, ascending);}
        
        // Merge the smaller list, pivot, and greater list back into the original list
        list.clear();
        list.addAll(smallerList,ascending);
        list.addLast(pivot);
        list.addAll(greaterList, ascending);
    }
    

     //mergesort
     public void mergeSort(LinkedList<T> list, boolean ascending) {
        if (list == null || list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;
        LinkedList<T> left = new LinkedList<>();
        LinkedList<T> right = new LinkedList<>();

        for (int i = 0; i < mid; i++) {
            left.addLast(list.getFirst());
            list.removeFirst();
        }
        while (!list.isEmpty()) {
            right.addLast(list.getFirst());
            list.removeFirst();
        }

        mergeSort(left, ascending);
        mergeSort(right, ascending);

        merge(list, left, right, ascending);
    }

    private void merge(LinkedList<T> list, LinkedList<T> left, LinkedList<T> right, boolean ascending) {
        int i = 0, j = 0, k = 0;

        while (i < left.size() && j < right.size()) {
            if(ascending){
            if (left.get(i).toString().compareTo(right.get(j).toString()) < 0) {
                list.set( left.get(i), k);
                i++;
            } else {
                list.set(right.get(j), k);
                j++;
            }
            k++;
        }else{
            if (left.get(i).toString().compareTo(right.get(j).toString()) > 0) {
                list.set( left.get(i), k);
                i++;
            } else {
                list.set(right.get(j), k);
                j++;
            }
            k++;
        }
    }

        while (i < left.size()) {
            list.set(left.get(i), k);
            i++;
            k++;
        }

        while (j < right.size()) {
            list.set(right.get(j), k);
            j++;
            k++;
        }
    }

    //shell-sort
    private void shellSort(LinkedList<T> list, boolean ascending){
        if (list == null || list.size() <= 1) {
            return;
        }

        int size = list.size();
        int gap = size / 2;

        while (gap > 0) {
            for (int i = gap; i < size; i++) {
                T current = list.get(i);
                int j = i;
                if(ascending){
                while (j >= gap && list.get(j - gap).toString().compareTo(current.toString()) > 0) {
                    list.set(list.get(j - gap), j);
                    j -= gap;
                }
            }else{
                while (j >= gap && list.get(j - gap).toString().compareTo(current.toString()) < 0) {
                    list.set(list.get(j - gap), j);
                    j -= gap;
                }
            }
                list.set(current, j);
            }
            gap /= 2;
        }
    }

    //insertion sort
    public void insertionSort (LinkedList<T> list, boolean ascending){
        if (list == null || list.size() <= 1) {
            return;
        }

        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            int j = i - 1;
            if(ascending){
            while (j >= 0 && list.get(j).toString().compareTo(current.toString()) > 0) {
                list.set(list.get(j), j+1);
                j--;
            }
        }else{
            while (j >= 0 && list.get(j).toString().compareTo(current.toString()) < 0) {
                list.set(list.get(j), j+1);
                j--;
        }
    }
            list.set(current,j+1);
        }
    }

    public boolean checkIfSorted(LinkedList<T> list){
        for(int i=0;i<list.size()-1;i++){
            if(list.get(i).toString().compareTo(list.get(i+1).toString())>0){
                return false;
            }
        }
        return true;
    }
}