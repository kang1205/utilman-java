package com.kang.utilman;

import org.junit.Assert;
import org.junit.Test;

import static com.kang.utilman.Arrman.*;

public class ArrmanTests {

    @Test
    public void testSize() {

        Assert.assertEquals(0, size((String[]) null));
        Assert.assertEquals(0, size(new String[0]));
        Assert.assertEquals(2, size(new String[]{"hello", "world"}));

    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(isEmpty((String[]) null));
        Assert.assertTrue(isEmpty(new String[0]));
        Assert.assertFalse(isEmpty(new String[]{"hello", "world"}));
    }

    @Test
    public void testGet() {
        Assert.assertFalse(get((String[]) null, 1).isPresent());
        Assert.assertFalse(get(new String[0], 1).isPresent());
        Assert.assertTrue(get(new String[]{"hello", "world"}, 1).isPresent());
        Assert.assertEquals("hello", get(new String[]{"hello", "world"}, 0).get());
        Assert.assertEquals("world", get(new String[]{"hello", "world"}, -1).get());
        Assert.assertFalse(get(new String[]{"hello", "world"}, 2).isPresent());

    }

}
