package com.goodworkalan.favorites;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.goodworkalan.favorites.Favorites;

public class FavoritesTest
{
    @Test
    public void favorites()
    {
        Favorites favorites = new Favorites();
        favorites.put(String.class, "Hello, World!");
        assertEquals(favorites.get(String.class), "Hello, World!");
        
        List<String> strings = Arrays.asList("a", "b", "c");
        favorites.put(new Favorites.Key<List<String>>() {}, strings);
        strings = favorites.get(new Favorites.Key<List<String>>() { });
        assertEquals(strings.get(0), "a");
    }
    
    @SuppressWarnings("unchecked")
    @Test(expectedExceptions=RuntimeException.class)
    public void noType()
    {
        new Favorites.Key() { };
    }
}
