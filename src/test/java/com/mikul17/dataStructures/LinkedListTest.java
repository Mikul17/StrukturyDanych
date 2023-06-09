package com.mikul17.dataStructures;

import org.example.LinkedList;
import org.example.SortingAlgorithm;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {
    LinkedList<Integer> quickSort = new LinkedList<>();
    LinkedList<Integer> mergeSort = new LinkedList<>();
    LinkedList<Integer> shellSort = new LinkedList<>();
    LinkedList<Integer> insertionSort = new LinkedList<>();

    // Testing methods for adding elements to the list
    @Test
    public void testAddFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testAddLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testAddIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.add(5, 1);
        assertEquals(5, list.get(1).intValue());
    }

    // Testing methods for getting elements from the list
    @Test
    public void testGet() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(1, list.get(2).intValue());
    }

    @Test
    public void testGetSelectedElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");
        assertEquals("a", list.get("a"));
    }

    @Test
    public void testGetFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.getFirst().intValue());
    }

    @Test
    public void testGetLast() {
        LinkedList<Float> list = new LinkedList<>();
        list.addFirst(1.1f);
        list.addFirst(2.2f);
        list.addFirst(3.3f);
        assertEquals(1.1f, list.getLast(), 0.0f);
    }

    // Testing methods for removing elements from the list
    @Test
    public void testRemoveFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.removeFirst();
        assertEquals(2, list.size());
        assertEquals(2, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());
        assertNull(list.get(2));
    }

    @Test
    public void testRemoveLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.removeLast();
        assertEquals(2, list.size());
        assertEquals(3, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
        assertNull(list.get(2));
    }

    @Test
    public void testRemoveIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.remove(1);
        assertEquals(1, list.get(1).intValue());
    }

    @Test
    public void removeSelectedElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");
        list.remove("b");
        assertNull(list.get("b"));
    }

    @Test
    public void testIsEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.addFirst(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.clear();
        assertEquals(0, list.size());
    }

    @Before
    public void setUpSorting() {
        Integer[] values = { 7, 1, 3, 4, 5, 6 };

        for (int i = 0; i < values.length; i++) {
            quickSort.addLast(values[i]);
            mergeSort.addLast(values[i]);
            shellSort.addLast(values[i]);
            insertionSort.addLast(values[i]);
        }
    }

    @Test
    public void testSortingAlgorithmsByValueAscending() {
        quickSort.sort(SortingAlgorithm.quickSort, quickSort, true);
        mergeSort.sort(SortingAlgorithm.mergeSort, mergeSort, true);
        shellSort.sort(SortingAlgorithm.shellSort, shellSort, true);
        insertionSort.sort(SortingAlgorithm.insertionSort, insertionSort, true);

        assertEquals(1, (int) quickSort.get(0));
        assertEquals(1, (int) mergeSort.getT(4));
        assertEquals(1, (int) shellSort.get(0));
        assertEquals(1, (int) insertionSort.get(0));

        assertEquals(7, (int) quickSort.get(quickSort.size() - 1));
        assertEquals(7, (int) mergeSort.get(0)); // get(mergeSort.size() - 1));
        assertEquals(7, (int) shellSort.get(shellSort.size() - 1));
        assertEquals(7, (int) insertionSort.get(insertionSort.size() - 1));

    }

    @Test
    public void testSortingAlgorithmsByValueDescending() {
        quickSort.sort(SortingAlgorithm.quickSort, quickSort, false);
        mergeSort.sort(SortingAlgorithm.mergeSort, mergeSort, false);
        shellSort.sort(SortingAlgorithm.shellSort, shellSort, false);
        insertionSort.sort(SortingAlgorithm.insertionSort, insertionSort, false);

        assertEquals(7, (int) quickSort.get(0));
        assertEquals(7, (int) mergeSort.get(0));
        assertEquals(7, (int) shellSort.get(0));
        assertEquals(7, (int) insertionSort.get(0));

        assertEquals(1, (int) quickSort.get(quickSort.size() - 1));
        assertEquals(1, (int) mergeSort.getT(4));
        assertEquals(1, (int) shellSort.get(shellSort.size() - 1));
        assertEquals(1, (int) insertionSort.get(insertionSort.size() - 1));

    }
}