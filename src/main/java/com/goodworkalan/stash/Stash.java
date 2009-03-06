package com.goodworkalan.stash;

import java.util.HashMap;
import java.util.Map;

import com.goodworkalan.ilk.Ilk;
import com.goodworkalan.ilk.UncheckedCast;

/**
 * A type-safe heterogeneous collection. 
 * 
 * @author Alan Gutierrez
 *
 * @param <K> The key type.
 */
public class Stash<K>
{
    /** Map of keys to type mapped objects. */
    private final Map<K, Map<Ilk.Key, Object>> map = new HashMap<K, Map<Ilk.Key, Object>>();

    /**
     * Map the given value of the type given by the <code>Ilk.Key</code> to the
     * given key.
     * 
     * @param <T>
     *            The type of the value.
     * @param key
     *            The key.
     * @param ilkKey
     *            The type token.
     * @param value
     *            The value.
     */
    private <T> void put(K key, Ilk.Key ilkKey, T value)
    {
        Map<Ilk.Key, Object> favorites = map.get(key);
        if (favorites == null)
        {
            favorites = new HashMap<Ilk.Key, Object>();
            map.put(key, favorites);
        }
        favorites.put(ilkKey, value);
    }

    /**
     * Map the given value of the type given by the <code>Ilk</code> super type
     * token to the given key.
     * 
     * @param <T>
     *            The type of the value.
     * @param key
     *            The key.
     * @param ilkKey
     *            The super type token of the value.
     * @param value
     *            The value.
     */
    public <T> void put(K key, Ilk<T> ilk, T value)
    {
        put(key, ilk.key, value);
    }
    
    /**
     * Map the given value of the type given by the class to the given key.
     * 
     * @param <T>
     *            The type of the value.
     * @param key
     *            The key.
     * @param typeClass
     *            The class of the value.
     * @param value
     *            The value.
     */
    public <T> void put(K key, Class<T> typeClass, T value)
    {
        put(key, new Ilk.Key(typeClass), value);
    }

    /**
     * Get the value of the given key and the type given by the
     * <code>Ilk.Key</code>.
     * 
     * @param key
     *            The key.
     * @param typeKey
     *            The type token of the value.
     * @return The value or null if none exists in the container.
     */
    private Object get(K key, Ilk.Key typeKey)
    {
        Map<Ilk.Key, Object> favorites = map.get(key);
        if (favorites == null)
        {
            return null;
        }
        return favorites.get(typeKey);
    }

    /**
     * Get the value of the given key and the type given by the <code>Ilk</code>
     * super type token.
     * 
     * @param key
     *            The key.
     * @param ilk
     *            The super type token of the value.
     * @return The value or null if none exists in the container.
     */
    public <T> T get(K key, Ilk<T> ilk)
    {
        return new UncheckedCast<T>().cast(get(key, ilk.key)); 
    }

    /**
     * Get the value of the given key and the given class..
     * 
     * @param key
     *            The key.
     * @param typeClass
     *            The class of the type.
     * @return The value or null if none exists in the container.
     */
    public <T> T get(K key, Class<T> typeClass)
    {
        return typeClass.cast(get(key, new Ilk.Key(typeClass)));
    }
}
