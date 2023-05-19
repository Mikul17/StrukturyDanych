package com.mikul17.dataStructures;


import org.example.Hashmap.Hashmap;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashmapTest {
    Hashmap<String, Integer> map;
    static class TestClass {
        public TestClass() {
        }
    }

    //put tests
    @Test
    public void testPut() {
        map = new Hashmap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertEquals(3, map.size());
    }

    @Test
    public void testPutSameKey() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", 1);
        map.put("a", 2);
        assertEquals(1, map.size());
    }

    @Test
    public void testPutNull() {
        Hashmap<String, Integer> map = new Hashmap<>();
        assertThrows(IllegalArgumentException.class, () -> map.put(null, 1));
    }

    @Test
    public void testPutNullValue() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", null);
        assertEquals(1, map.size());
    }

    @Test
    public void testPutNullKeyAndValue() {
        Hashmap<String, Integer> map = new Hashmap<>();
        assertThrows(IllegalArgumentException.class, () -> map.put(null, null));
    }

    @Test
    public void testPutKeyIsInteger() {
        Hashmap<Integer, Integer> map = new Hashmap<>();
        map.put(1, 3);
        assertEquals(1, map.size());
    }
    @Test
    public void testPutKeyIsClassInstance() {
        Hashmap<TestClass, Integer> map = new Hashmap<>();
        TestClass testClass = new TestClass();
        map.put(testClass, 3);
        assertEquals(1, map.size());
    }

    //get tests
    @Test
    public void testGet() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertEquals(Integer.valueOf(1), map.get("a"));
    }
    @Test
    public void testGetOutOfBounds() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", 1);
        map.put("b", 2);
        assertNull(map.get("c"));
    }
    @Test
    public void testGetWhenKeyIsClass() {
        Hashmap<TestClass, Integer> map = new Hashmap<>();
        TestClass testClass = new TestClass();
        map.put(testClass, 3);
        map.get(testClass);
        assertEquals(3, map.get(testClass).intValue());
    }
    @Test
    public void testGetWhenValueIsClass(){
        Hashmap<Integer,TestClass> map = new Hashmap<>();
        TestClass testClass = new TestClass();
        map.put(3, testClass);
        assertEquals(testClass, map.get(3));
    }
    @Test
    public void testGetNullKey() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", 1);
        map.put("b", 2);
        assertThrows(IllegalArgumentException.class, () -> map.get(null));
    }

    //resize test
    @Test
    public void testResize() {
        Hashmap<String, Integer> map = new Hashmap<>(2, 0.5f);
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        assertEquals(3, map.size());
        assertEquals(8, map.getCapacity());
    }
    @Test
    public void testResizeWithDefaultInitialCapacity() {
        Hashmap<String, Integer> map = new Hashmap<>();
        for (int i = 0; i < 17; i++) {
            map.put(String.valueOf(i), 1);
        }
        assertEquals(17, map.size());
        assertEquals(32, map.getCapacity());
    }
    @Test
    public void testResizeWithModifiedLoadFactor() {
        Hashmap<String, Integer> map = new Hashmap<>(0.5f);
        for (int i = 0; i < 17; i++) {
            map.put(String.valueOf(i), 1);
        }
        assertEquals(17, map.size());
        assertEquals(64, map.getCapacity());
    }
    @Test
    public void testResizeDown() {
        Hashmap<String, Integer> map = new Hashmap<>(2, 0.5f);
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.remove("a");
        map.remove("c");
        assertEquals(1, map.size());
        assertEquals(4, map.getCapacity());
    }
    @Test
    public void testResizeDownWithBiggerData() {
        Hashmap<String, Integer> map = new Hashmap<>();
        for (int i = 0; i < 150; i++) {
            map.put(String.valueOf(i), i);
        }
        for (int i = 0; i < 120; i++) {
            map.remove(String.valueOf(i));
        }
        assertEquals(30, map.size());
        assertEquals(64, map.getCapacity());
    }

    //remove tests
    @Test
    public void testRemove() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.remove("a");
        assertEquals(1, map.size());
    }
    @Test
    public void testRemoveNullKey() {
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", 1);
        map.put("b", 2);
        assertThrows(IllegalArgumentException.class, () -> map.remove(null));
    }
    @Test
    public void testRemoveNonExistingKey(){
        Hashmap<String, Integer> map = new Hashmap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.remove("c");
        assertEquals(2, map.size());
        assertEquals(4, map.getCapacity());
    }
}
