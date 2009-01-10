package com.goodworkalan.stash;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public class Stash
{
    private final Map<Key, Map<java.lang.reflect.Type, Object>> map = new HashMap<Key, Map<java.lang.reflect.Type, Object>>();
    
    private <T> void put(Key flag, java.lang.reflect.Type key, T value)
    {
        Map<java.lang.reflect.Type, Object> favorites = map.get(flag);
        if (favorites == null)
        {
            favorites = new HashMap<java.lang.reflect.Type, Object>();
            map.put(flag, favorites);
        }
        favorites.put(key, value);
    }
    
    public <T> void put(Key flag, Type<T> key, T value)
    {
        put(flag, key.getType(), value);
    }
    
    public <T> void put(Key flag, Class<T> key, T value)
    {
        put(flag, (java.lang.reflect.Type) key, value);
    }
    
    private Object get(Key flag, java.lang.reflect.Type type)
    {
        Map<java.lang.reflect.Type, Object> favorites = map.get(flag);
        if (favorites == null)
        {
            return null;
        }
        return favorites.get(type);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(Key flag, Type<T> key)
    {
        return (T) get(flag, key.getType()); 
    }
    
    public <T> T get(Key flag, Class<T> key)
    {
        return key.cast(get(flag, (java.lang.reflect.Type) key));
    }
    
    public final static class Key
    {
    }

    public abstract static class Type<T>
    {
        private final java.lang.reflect.Type type;
        
        public Type()
        {
            // Give me class information.  
            Class<?> klass = getClass();  
           
            // See that I have created an anonymous subclass of TypeReference in the  
            // main method. Hence, to get the TypeReference itself, I need superclass.  
            // Furthermore, to get Type information, you should call  
            // getGenericSuperclass() instead of getSuperclass().  
            java.lang.reflect.Type superClass = klass.getGenericSuperclass();  
           
            if (superClass instanceof Class)
            {  
                // Type has four subinterface:  
                // (1) GenericArrayType: component type is either a  
                // parameterized type or a type variable. Parameterized type is a class  
                // or interface with its actual type argument, e.g., ArrayList<String>.  
                // Type variable is unqualified identifier like T or V.  
                //  
                // (2) ParameterizedType: see (1).  
                //  
                // (3) TypeVariable<D>: see (1).  
                //  
                // (4) WildcardType: ?  
                //  
                // and one subclass:  
                // (5) Class.  
                //  
                // If TypeReference is created by 'new TypeReference() { }', then  
                // superClass would be just an instance of Class instead of one of the  
                // interfaces described above. In that case, because I don't have type  
                // passed to TypeReference, an exception should be raised.  
                throw new RuntimeException("Missing Type Parameter");  
            }  
           
            // By superClass, we mean 'TypeReference<T>'. So, it is obvious that  
            // superClass is ParameterizedType.  
            ParameterizedType pt = (ParameterizedType) superClass;  
           
            // We have one type argument in TypeRefence<T>: T.  
            type = pt.getActualTypeArguments()[0];  
        }
        
        public java.lang.reflect.Type getType()
        {
            return type;
        }
    }
}
