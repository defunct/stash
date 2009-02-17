package com.goodworkalan.stash;

import java.util.HashMap;
import java.util.Map;

import com.goodworkalan.ilk.Ilk;
import com.goodworkalan.ilk.UncheckedCast;

public class Stash
{
    private final Map<Key, Map<Ilk.Key, Object>> map = new HashMap<Key, Map<Ilk.Key, Object>>();
    
    private <T> void put(Key flag, Ilk.Key key, T value)
    {
        Map<Ilk.Key, Object> favorites = map.get(flag);
        if (favorites == null)
        {
            favorites = new HashMap<Ilk.Key, Object>();
            map.put(flag, favorites);
        }
        favorites.put(key, value);
    }
    
    public <T> void put(Key flag, Ilk<T> ilk, T value)
    {
        put(flag, ilk.key, value);
    }
    
    public <T> void put(Key flag, Class<T> typeClass, T value)
    {
        put(flag, new Ilk.Key(typeClass), value);
    }
    
    private Object get(Key flag, Ilk.Key key)
    {
        Map<Ilk.Key, Object> favorites = map.get(flag);
        if (favorites == null)
        {
            return null;
        }
        return favorites.get(key);
    }

    public <T> T get(Key flag, Ilk<T> ilk)
    {
        return new UncheckedCast<T>().cast(get(flag, ilk.key)); 
    }
    
    public <T> T get(Key flag, Class<T> typeClass)
    {
        return typeClass.cast(get(flag, new Ilk.Key(typeClass)));
    }
    
    public final static class Key
    {
    }
}
