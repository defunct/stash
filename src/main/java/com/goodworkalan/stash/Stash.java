package com.goodworkalan.stash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.goodworkalan.ilk.Ilk;

/**
 * A type-safe heterogeneous collection.
 * 
 * @author Alan Gutierrez
 */
public class Stash implements Serializable {
    /** The serial version id. */
    private static final long serialVersionUID = 1L;

    /** Map of keys to type mapped objects. */
    private final Map<Key, Ilk.Box> map = new HashMap<Key, Ilk.Box>();

    /** Create an empty type-safe heterogeneous collection. */
    public Stash() {
    }

    /**
     * Map the given value of the type given by the <code>Ilk</code> super type
     * token to the given key.
     * 
     * @param <T>
     *            The type of the value.
     * @param key
     *            The key.
     * @param ilk
     *            The super type token of the value.
     * @param value
     *            The value.
     */
    public <T> void put(Key key, Ilk<T> ilk, T value) {
        map.put(key, ilk.box(value));
    }

    /**
     * Map the given value of the type given by the class to the given key.
     * 
     * @param <T>
     *            The type of the value.
     * @param key
     *            The key.
     * @param type
     *            The class of the value.
     * @param value
     *            The value.
     */
    public <T> void put(Key key, Class<T> type, T value) {
        put(key, new Ilk<T>(type), value);
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
    public <T> T get(Key key, Ilk<T> ilk) {
        Ilk.Box box = map.get(key);
        if (box == null) {
            return null;
        }
        return box.cast(ilk);
    }

    /**
     * Get the value of the given key and the given class..
     * 
     * @param key
     *            The key.
     * @param type
     *            The class of the type.
     * @return The value or null if none exists in the container.
     */
    public <T> T get(Key key, Class<T> type) {
        return get(key, new Ilk<T>(type));
    }

    /**
     * The key type used to identify properties stored in the type-safe
     * container. The key type uses identity equality and hash code. An item can
     * only be retrieved from a stash with the same key instance that was used
     * to add it.
     * 
     * @author Alan Gutierrez
     */
    public final static class Key implements Serializable {
        /** The serial version id. */
        private static final long serialVersionUID = 1L;

        /** Create a key. */
        public Key() {
        }
    }
}
