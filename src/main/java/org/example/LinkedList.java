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

    public int size(){
        return size;
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
}
