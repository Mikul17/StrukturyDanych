package com.mikul17.dataStructures;

import org.example.LinkedHashMap.LinkedHashMap;
import org.example.SortingAlgorithm;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedHashMapTest {
    LinkedHashMap<String, Integer> map=new LinkedHashMap<>();
    LinkedHashMap<String,Integer> quickSortMap=new LinkedHashMap<>();
    LinkedHashMap<String,Integer> mergeSortMap=new LinkedHashMap<>();
    LinkedHashMap<String,Integer> shellSortMap=new LinkedHashMap<>();
    LinkedHashMap<String,Integer> insertionSortMap=new LinkedHashMap<>();
    LinkedHashMap<String,Integer> bubbleSortMap=new LinkedHashMap<>();
    static class TestClass {
        public TestClass() {
        }
    }

    //put methods
    @Test
    public void testPut(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        assertEquals(3, map.size());
        assertEquals(3, map.entryListSize());
    }
    @Test
    public void testPutSameKey(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        map.put("b", 5);
        assertEquals(3, map.size());
        assertEquals(3, map.entryListSize());
        assertEquals(5, (int)map.get("b"));
    }
    @Test
    public void testPutNullKey(){
        assertThrows(IllegalArgumentException.class, () -> map.put(null, 5));
    }
    @Test
    public void testPutNullValue(){
        map.put("a",null);
        assertEquals(1, map.size());
        assertEquals(1, map.entryListSize());
    }
    @Test
    public void testPutNullKeyAndValue(){
        assertThrows(IllegalArgumentException.class, () -> map.put(null, null));
    }
    @Test
    public void testPutKeyIsInteger(){
        LinkedHashMap<Integer, Integer> map=new LinkedHashMap<>();
        map.put(1, 3);
        assertEquals(1, map.size());
        assertEquals(1, map.entryListSize());
    }
    @Test
    public void testPutKeyIsClassInstance(){
        LinkedHashMap<TestClass, Integer> map=new LinkedHashMap<>();
        map.put(new TestClass(), 3);
        assertEquals(1, map.size());
        assertEquals(1, map.entryListSize());
    }
    //remove methods
    @Test
    public void testRemove(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        map.remove("a");
        assertEquals(2, map.size());
        assertEquals(2, map.entryListSize());
    }
    @Test
    public void testRemoveNullKey(){
        assertThrows(IllegalArgumentException.class, () -> map.remove(null));
    }
    @Test
    public void testRemoveNonExistingKey(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        map.remove("c");
        assertEquals(3, map.size());
        assertEquals(3, map.entryListSize());
    }
    //get methods
    @Test
    public void testGet(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        assertEquals(7, (int)map.get("a"));
        assertEquals(1, (int)map.get("f"));
        assertEquals(3, (int)map.get("b"));
    }
    @Test
    public void testGetNullKey(){
        assertThrows(IllegalArgumentException.class, () -> map.get(null));
    }
    @Test
    public void testGetNonExistingKey(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        assertNull(map.get("c"));
    }
    @Test
    public void testGetKeyIsInteger(){
        LinkedHashMap<Integer, Integer> map=new LinkedHashMap<>();
        map.put(1, 3);
        assertEquals(3, (int)map.get(1));
    }
    @Test
    public void testGetKeyIsClassInstance(){
        LinkedHashMap<TestClass, Integer> map=new LinkedHashMap<>();
        TestClass testClass=new TestClass();
        map.put(testClass, 3);
        assertEquals(3, (int)map.get(testClass));
    }
    //containsKey methods
    @Test
    public void testContainsKey(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        assertTrue(map.containsKey("a"));
        assertTrue(map.containsKey("f"));
        assertTrue(map.containsKey("b"));
    }
    @Test
    public void testContainsKeyNullKey(){
        assertThrows(IllegalArgumentException.class, () -> map.containsKey(null));
    }
    @Test
    public void testContainsKeyNonExistingKey(){
        map.put("a", 7);
        map.put("f", 1);
        map.put("b", 3);
        assertFalse(map.containsKey("c"));
    }

    //sort methods
    @Before
    public void setUpSorting(){
        String[] keys={"a", "f", "b", "c", "z", "m"};
        Integer[] values={7, 1, 3, 4, 5, 6};

        for(int i=0; i<keys.length; i++){
            quickSortMap.put(keys[i], values[i]);
            mergeSortMap.put(keys[i], values[i]);
            shellSortMap.put(keys[i], values[i]);
            insertionSortMap.put(keys[i], values[i]);
            bubbleSortMap.put(keys[i], values[i]);
        }
    }
    @Test
    public void testSortingAlgorithmsByKeyAscending(){
        quickSortMap.sort(SortingAlgorithm.quickSort, true,true);
        mergeSortMap.sort(SortingAlgorithm.mergeSort, true,true);
        shellSortMap.sort(SortingAlgorithm.shellSort, true,true);
        insertionSortMap.sort(SortingAlgorithm.insertionSort, true,true);
        bubbleSortMap.sort(SortingAlgorithm.bubbleSort, true,true);

        assertEquals("a", quickSortMap.getEntry(0).getKey());
        assertEquals("a", mergeSortMap.getEntry(0).getKey());
        assertEquals("a", shellSortMap.getEntry(0).getKey());
        assertEquals("a", insertionSortMap.getEntry(0).getKey());
        assertEquals("a", bubbleSortMap.getEntry(0).getKey());

        assertEquals("z", quickSortMap.getEntry(quickSortMap.size()-1).getKey());
        assertEquals("z", mergeSortMap.getEntry(mergeSortMap.size()-1).getKey());
        assertEquals("z", shellSortMap.getEntry(shellSortMap.size()-1).getKey());
        assertEquals("z", insertionSortMap.getEntry(insertionSortMap.size()-1).getKey());
        assertEquals("z", bubbleSortMap.getEntry(bubbleSortMap.size()-1).getKey());
    }
    @Test
    public void testSortingAlgorithmsByValueAscending(){
        quickSortMap.sort(SortingAlgorithm.quickSort, false,true);
        mergeSortMap.sort(SortingAlgorithm.mergeSort, false,true);
        shellSortMap.sort(SortingAlgorithm.shellSort, false,true);
        insertionSortMap.sort(SortingAlgorithm.insertionSort, false,true);
        bubbleSortMap.sort(SortingAlgorithm.bubbleSort, false,true);

        assertEquals(1, (int)quickSortMap.getEntry(0).value);
        assertEquals(1, (int)mergeSortMap.getEntry(0).value);
        assertEquals(1, (int)shellSortMap.getEntry(0).value);
        assertEquals(1, (int)insertionSortMap.getEntry(0).value);
        assertEquals(1, (int)bubbleSortMap.getEntry(0).value);

        assertEquals(7, (int)quickSortMap.getEntry(quickSortMap.size()-1).value);
        assertEquals(7, (int)mergeSortMap.getEntry(mergeSortMap.size()-1).value);
        assertEquals(7, (int)shellSortMap.getEntry(shellSortMap.size()-1).value);
        assertEquals(7, (int)insertionSortMap.getEntry(insertionSortMap.size()-1).value);
        assertEquals(7, (int)bubbleSortMap.getEntry(bubbleSortMap.size()-1).value);
    }
    @Test
    public void testSortingAlgorithmsByKeyDescending(){
        quickSortMap.sort(SortingAlgorithm.quickSort, true,false);
        mergeSortMap.sort(SortingAlgorithm.mergeSort, true,false);
        shellSortMap.sort(SortingAlgorithm.shellSort, true,false);
        insertionSortMap.sort(SortingAlgorithm.insertionSort, true,false);
        bubbleSortMap.sort(SortingAlgorithm.bubbleSort, true,false);

        assertEquals("z", quickSortMap.getEntry(0).getKey());
        assertEquals("z", mergeSortMap.getEntry(0).getKey());
        assertEquals("z", shellSortMap.getEntry(0).getKey());
        assertEquals("z", insertionSortMap.getEntry(0).getKey());
        assertEquals("z", bubbleSortMap.getEntry(0).getKey());

        assertEquals("a", quickSortMap.getEntry(quickSortMap.size()-1).getKey());
        assertEquals("a", mergeSortMap.getEntry(mergeSortMap.size()-1).getKey());
        assertEquals("a", shellSortMap.getEntry(shellSortMap.size()-1).getKey());
        assertEquals("a", insertionSortMap.getEntry(insertionSortMap.size()-1).getKey());
        assertEquals("a", bubbleSortMap.getEntry(bubbleSortMap.size()-1).getKey());
    }
    @Test
    public void testSortingAlgorithmsByValueDescending(){
        quickSortMap.sort(SortingAlgorithm.quickSort, false,false);
        mergeSortMap.sort(SortingAlgorithm.mergeSort, false,false);
        shellSortMap.sort(SortingAlgorithm.shellSort, false,false);
        insertionSortMap.sort(SortingAlgorithm.insertionSort, false,false);
        bubbleSortMap.sort(SortingAlgorithm.bubbleSort, false,false);

        assertEquals(7, (int)quickSortMap.getEntry(0).value);
        assertEquals(7, (int)mergeSortMap.getEntry(0).value);
        assertEquals(7, (int)shellSortMap.getEntry(0).value);
        assertEquals(7, (int)insertionSortMap.getEntry(0).value);
        assertEquals(7, (int)bubbleSortMap.getEntry(0).value);

        assertEquals(1, (int)quickSortMap.getEntry(quickSortMap.size()-1).value);
        assertEquals(1, (int)mergeSortMap.getEntry(mergeSortMap.size()-1).value);
        assertEquals(1, (int)shellSortMap.getEntry(shellSortMap.size()-1).value);
        assertEquals(1, (int)insertionSortMap.getEntry(insertionSortMap.size()-1).value);
        assertEquals(1, (int)bubbleSortMap.getEntry(bubbleSortMap.size()-1).value);
    }
}
