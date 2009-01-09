package com.goodworkalan.favorites;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Favorites
{
    private final Map<Type, Object> map = new HashMap<Type, Object>();
    
    public <T> void put(Key<T> key, T value)
    {
        map.put(key.getType(), value);
    }
    
    public <T> void put(Class<T> key, T value)
    {
        map.put(key, value);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(Key<T> key)
    {
        return (T) map.get(key.getType()); 
    }
    
    public <T> T get(Class<T> key)
    {
        return key.cast(map.get(key));
    }

    public abstract static class Key<T>
    {
        private final Type type;
        
        public Key()
        {
            // Give me class information.  
            Class<?> klass = getClass();  
           
            // See that I have created an anonymous subclass of TypeReference in the  
            // main method. Hence, to get the TypeReference itself, I need superclass.  
            // Furthermore, to get Type information, you should call  
            // getGenericSuperclass() instead of getSuperclass().  
            Type superClass = klass.getGenericSuperclass();  
           
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
        
        public Type getType()
        {
            return type;
        }
    }
}
