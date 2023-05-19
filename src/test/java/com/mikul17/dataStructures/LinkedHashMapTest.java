package com.mikul17.dataStructures;

import org.example.LinkedHashMap.LinkedHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedHashMapTest {
    LinkedHashMap<String, Integer> map=new LinkedHashMap<>();
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
}
