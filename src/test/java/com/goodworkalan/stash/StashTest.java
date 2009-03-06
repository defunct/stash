package com.goodworkalan.stash;


import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.goodworkalan.ilk.Ilk;

public class StashTest
{
    @Test
    public void stash()
    {
        Stash.Key key = new Stash.Key();
        Stash stash = new Stash();
        stash.put(key, String.class, "Hello, World!");
        assertEquals(stash.get(key, String.class), "Hello, World!");
        
        List<String> strings = Arrays.asList("a", "b", "c");
        stash.put(key, new Ilk<List<String>>() {}, strings);
        strings = stash.get(key, new Ilk<List<String>>() { });
        assertEquals(strings.get(0), "a");
    }
}
