package com.kang.utilman;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static com.kang.utilman.Collman.*;

public class CollmanTests {

    @Test
    public void testSize() {

        Assert.assertEquals(0, size((Map) null));
        Assert.assertEquals(0, size(new HashMap<>()));
        Assert.assertEquals(1, size(new HashMap<String, String>() {{
            put("k1", "v1");
        }}));

        Assert.assertEquals(0, size((Collection) null));
        Assert.assertEquals(0, size(new ArrayList<>()));
        Assert.assertEquals(1, size(new ArrayList<String>() {{
            add("hello");
        }}));
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(isEmpty((Map) null));
        Assert.assertTrue(isEmpty(new HashMap<>()));
        Assert.assertFalse(isEmpty(new HashMap<Integer, Integer>() {{
            put(1, 1);
        }}));

        Assert.assertTrue(isEmpty((Collection) null));
        Assert.assertTrue(isEmpty(new ArrayList<>()));
        Assert.assertFalse(isEmpty(new ArrayList<Integer>() {{
            add(1);
        }}));
    }

    @Test
    public void testGet() {

        Assert.assertFalse(get((Map<String, String>) null, null).isPresent());
        Map<String, String> map = new HashMap<String, String>() {{
            put("k", "v");
        }};
        Assert.assertFalse(get(map, null).isPresent());
        Assert.assertFalse(get(map, "kkk").isPresent());
        Assert.assertEquals("v", get(map, "k").get());

        Assert.assertFalse(get((List) null, 1).isPresent());
        Assert.assertFalse(get(new ArrayList<>(), 1).isPresent());
        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        Integer v = get(list, 0).get();
        Assert.assertTrue(1 == v);
        v = get(list, -1).get();
        Assert.assertTrue(3 == v);
    }

    @Test
    public void testContains() {
        Assert.assertFalse(contains((List) null, ""));
        Assert.assertFalse(contains(new ArrayList<>(), ""));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Assert.assertTrue(contains(list, 1));
        Assert.assertFalse(contains(list, 3));
        Assert.assertFalse(contains(list, null));

        Assert.assertFalse(containsKey(null, "k"));
        Assert.assertFalse(containsValue(null, "k"));
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 11);
        map.put(2, 22);
        Assert.assertTrue(containsKey(map, 1));
        Assert.assertTrue(containsValue(map, 22));
        Assert.assertFalse(containsKey(map, 3));
        Assert.assertFalse(containsValue(map, 33));
        Assert.assertFalse(containsKey(map, null));
        Assert.assertFalse(containsValue(map, null));
        map.put(3, null);
        map.put(null, 44);
        Assert.assertTrue(containsKey(map, null));
        Assert.assertTrue(containsValue(map, null));
    }

}
