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
        Stash<Integer> stash = new Stash<Integer>();
        stash.put(1, String.class, "Hello, World!");
        assertEquals(stash.get(1, String.class), "Hello, World!");
        
        List<String> strings = Arrays.asList("a", "b", "c");
        stash.put(1, new Ilk<List<String>>() {}, strings);
        strings = stash.get(1, new Ilk<List<String>>() { });
        assertEquals(strings.get(0), "a");
    }
}
