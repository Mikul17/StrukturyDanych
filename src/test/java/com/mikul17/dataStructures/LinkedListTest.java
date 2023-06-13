package com.mikul17.dataStructures;

import org.example.LinkedList;
import org.example.SortingAlgorithm;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {

    LinkedList<Integer> sortingListInteger = new LinkedList<>();
    LinkedList<String> sortingListString = new LinkedList<>();
    LinkedList<Double> sortingListDouble = new LinkedList<>();


    //Testing methods for adding elements to the list
    @Test
    public void testAddLast () {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testAddIndex () {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.add(5, 1);
        assertEquals(5, list.get(1).intValue());
    }

    //Testing methods for getting elements from the list
    @Test
    public void testGet () {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(1, list.get(2).intValue());
    }

    @Test
    public void testGetSelectedElement () {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");
        assertEquals("a", list.get("a"));
    }

    @Test
    public void testGetFirst () {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.getFirst().intValue());
    }

    @Test
    public void testGetLast () {
        LinkedList<Float> list = new LinkedList<>();
        list.addFirst(1.1f);
        list.addFirst(2.2f);
        list.addFirst(3.3f);
        assertEquals(1.1f, list.getLast(), 0.0f);
    }

    //Testing methods for removing elements from the list
    @Test
    public void testRemoveFirst () {
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
    public void testRemoveLast () {
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
    public void testRemoveIndex () {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.remove(1);
        assertEquals(1, list.get(1).intValue());
    }

    @Test
    public void removeSelectedElement () {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("a");
        list.addFirst("b");
        list.addFirst("c");
        list.remove("b");
        assertNull(list.get("b"));
    }

    @Test
    public void testIsEmpty () {
        LinkedList<Integer> list = new LinkedList<>();
        assertTrue(list.isEmpty());
        list.addFirst(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear () {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.clear();
        assertEquals(0, list.size());
    }

    //Testing methods for sorting the list
    //sort methods
    @Before
    public void setUpSorting () {
        Integer[] values = {7, 1, 3, 4, 5, 6};
        String[] strings = {"a", "f", "b", "c", "z", "m"};
        Double[] doubles = {2.0, 1.5, 17.3, 4.0, 0.2, 10.7};

        for (int i = 0; i < values.length; i++) {
            sortingListInteger.addLast(values[i]);
            sortingListString.addLast(strings[i]);
            sortingListDouble.addLast(doubles[i]);
        }
    }

    @Test
    public void testQuicksortAscending () {
        sortingListInteger.sort(SortingAlgorithm.quickSort, true);
        sortingListString.sort(SortingAlgorithm.quickSort, true);
        sortingListDouble.sort(SortingAlgorithm.quickSort, true);


        assertEquals(1, (int) sortingListInteger.get(0));
        assertEquals("a", sortingListString.get(0));
        assertEquals(0.2, sortingListDouble.get(0), 0.0);

        assertEquals(7, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("z", sortingListString.get(sortingListString.size() - 1));
        assertEquals(17.3, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);

    }

    @Test
    public void testQuicksortDescending() {
        sortingListInteger.sort(SortingAlgorithm.quickSort, false);
        sortingListString.sort(SortingAlgorithm.quickSort, false);
        sortingListDouble.sort(SortingAlgorithm.quickSort, false);

        assertEquals(7, (int) sortingListInteger.get(0));
        assertEquals("z", sortingListString.get(0));
        assertEquals(17.3, sortingListDouble.get(0), 0.0);

        assertEquals(1, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("a", sortingListString.get(sortingListString.size() - 1));
        assertEquals(0.2, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);
    }

    @Test
    public void testMergesortAscending(){
        sortingListInteger.sort(SortingAlgorithm.mergeSort, true);
        sortingListString.sort(SortingAlgorithm.mergeSort, true);
        sortingListDouble.sort(SortingAlgorithm.mergeSort, true);

        assertEquals(1, (int) sortingListInteger.get(0));
        assertEquals("a", sortingListString.get(0));
        assertEquals(0.2, sortingListDouble.get(0), 0.0);

        assertEquals(7, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("z", sortingListString.get(sortingListString.size() - 1));
        assertEquals(17.3, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);


    }

    @Test
    public void testMergesortDescending(){
        sortingListInteger.sort(SortingAlgorithm.mergeSort, false);
        sortingListString.sort(SortingAlgorithm.mergeSort, false);
        sortingListDouble.sort(SortingAlgorithm.mergeSort, false);

        assertEquals(7, (int) sortingListInteger.get(0));
        assertEquals("z", sortingListString.get(0));
        assertEquals(17.3, sortingListDouble.get(0), 0.0);

        assertEquals(1, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("a", sortingListString.get(sortingListString.size() - 1));
        assertEquals(0.2, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);
    }

    @Test
    public void testShellsortAscending(){
        sortingListInteger.sort(SortingAlgorithm.shellSort, true);
        sortingListString.sort(SortingAlgorithm.shellSort, true);
        sortingListDouble.sort(SortingAlgorithm.shellSort, true);

        assertEquals(1, (int) sortingListInteger.get(0));
        assertEquals("a", sortingListString.get(0));
        assertEquals(0.2, sortingListDouble.get(0), 0.0);

        assertEquals(7, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("z", sortingListString.get(sortingListString.size() - 1));
        assertEquals(17.3, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);
    }

    @Test
    public void testShellsortDescending(){
        sortingListInteger.sort(SortingAlgorithm.shellSort, false);
        sortingListString.sort(SortingAlgorithm.shellSort, false);
        sortingListDouble.sort(SortingAlgorithm.shellSort, false);

        assertEquals(7, (int) sortingListInteger.get(0));
        assertEquals("z", sortingListString.get(0));
        assertEquals(17.3, sortingListDouble.get(0), 0.0);

        assertEquals(1, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("a", sortingListString.get(sortingListString.size() - 1));
        assertEquals(0.2, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);
    }

    @Test
    public void testInsertionsortAscending(){
        sortingListInteger.sort(SortingAlgorithm.insertionSort, true);
        sortingListString.sort(SortingAlgorithm.insertionSort, true);
        sortingListDouble.sort(SortingAlgorithm.insertionSort, true);

        assertEquals(1, (int) sortingListInteger.get(0));
        assertEquals("a", sortingListString.get(0));
        assertEquals(0.2, sortingListDouble.get(0), 0.0);

        assertEquals(7, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("z", sortingListString.get(sortingListString.size() - 1));
        assertEquals(17.3, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);
    }

    @Test
    public void testInsertionsortDescending(){
        sortingListInteger.sort(SortingAlgorithm.insertionSort, false);
        sortingListString.sort(SortingAlgorithm.insertionSort, false);
        sortingListDouble.sort(SortingAlgorithm.insertionSort, false);

        assertEquals(7, (int) sortingListInteger.get(0));
        assertEquals("z", sortingListString.get(0));
        assertEquals(17.3, sortingListDouble.get(0), 0.0);

        assertEquals(1, (int) sortingListInteger.get(sortingListInteger.size() - 1));
        assertEquals("a", sortingListString.get(sortingListString.size() - 1));
        assertEquals(0.2, sortingListDouble.get(sortingListDouble.size() - 1), 0.0);
    }
}