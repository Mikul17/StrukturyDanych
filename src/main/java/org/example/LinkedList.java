package org.example;


public class LinkedList <T extends Comparable<T>>{
    private class Node{
        private T data;
        private Node next;
        private Node prev;

        public Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        private int compareValues(T value){
            return this.data.compareTo(value);
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
            return;
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
            return;
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

    private void swap(Node i, Node j){
        T temp = i.data;
        i.data = j.data;
        j.data = temp;
    }

    //Sorting methods

    public void sort(SortingAlgorithm algorithm, boolean ascending){
        switch(algorithm){
            case quickSort:
                quickSort(head, tail, ascending);
                break;
            case mergeSort:
                mergeSort(head, tail,ascending);
                break;
            case shellSort:
                shellSort(ascending);
                break;
            case insertionSort:
                insertionSort(ascending);
                break;
            default:
                System.out.println("Invalid sorting algorithm");
        }
    }

    //quicksort

    private void quickSort(Node head, Node tail, boolean ascending){
        if (head == null || tail == null || head == tail || head == tail.next){
            return;
        }
        Node pivot = partition(head, tail, ascending);
        quickSort(head, pivot.prev, ascending);
        quickSort(pivot.next, tail, ascending);
    }

    private Node partition(Node head, Node tail, boolean ascending) {
        Node i = head.prev;
        for (Node j = head; j != tail; j = j.next) {
            int comparison = j.data.compareTo(tail.data);
            if ((ascending && comparison <= 0) || (!ascending && comparison >= 0)) {
                i = (i == null) ? head : i.next;
                swap(i, j);
            }
        }
        i = (i == null) ? head : i.next;
        swap(i, tail);
        return i;
    }

    //merge sort
    private void mergeSort(Node head, Node tail, boolean ascending){
        if (head == null || tail == null || head == tail || head == tail.next){
            return;
        }
        Node mid = getMid(head, tail);
        mergeSort(head, mid, ascending);
        mergeSort(mid.next, tail, ascending);
        merge(head, mid, tail, ascending);
    }

    private void merge(Node head, Node mid, Node tail, boolean ascending) {
        Node left = head;
        Node right = mid.next;
        Node merged = null;
        Node current = null;

        while (left != mid.next && right != tail.next) {
            Node newNode;
            if ((ascending && left.data.compareTo(right.data) <= 0) ||
                    (!ascending && left.data.compareTo(right.data) >= 0)) {
                newNode = new Node(left.data);
                left = left.next;
            } else {
                newNode = new Node(right.data);
                right = right.next;
            }

            if (merged == null) {
                merged = newNode;
                current = newNode;
            } else {
                current.next = newNode;
                newNode.prev = current;
                current = newNode;
            }
        }

        while (left != mid.next) {
            Node newNode = new Node(left.data);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
            left = left.next;
        }

        while (right != tail.next) {
            Node newNode = new Node(right.data);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
            right = right.next;
        }

        // Link the merged list back to the original list
        current = merged;
        while (current != null) {
            head.data = current.data;
            current = current.next;
            head = head.next;
        }
    }

    private Node getMid (Node head, Node tail) {
        if (head == null){
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //shell's sort
    private void shellSort(boolean ascending){
        int gap = size / 2;
        while (gap > 0){
            for (int i = gap; i < size; i++){
                T temp = get(i);
                int j = i;
                while (j >= gap && ((ascending && get(j - gap).compareTo(temp) > 0) ||
                        (!ascending && get(j - gap).compareTo(temp) < 0))){
                    set(get(j - gap), j);
                    j -= gap;
                }
                set(temp, j);
            }
            gap /= 2;
        }
    }

    //insertion sort
    private void insertionSort(boolean ascending){
        for (int i = 1; i < size; i++){
            T temp = get(i);
            int j = i;
            while (j > 0 && ((ascending && get(j - 1).compareTo(temp) > 0) ||
                    (!ascending && get(j - 1).compareTo(temp) < 0))){
                set(get(j - 1), j);
                j--;
            }
            set(temp, j);
        }
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
}
