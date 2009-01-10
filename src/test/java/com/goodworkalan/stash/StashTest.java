package com.goodworkalan.stash;


import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.goodworkalan.stash.Stash;

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
        stash.put(key, new Stash.Type<List<String>>() {}, strings);
        strings = stash.get(key, new Stash.Type<List<String>>() { });
        assertEquals(strings.get(0), "a");
    }

    @SuppressWarnings("unchecked")
    @Test(expectedExceptions=RuntimeException.class)
    public void noType()
    {
        new Stash.Type() { };
    }
}
