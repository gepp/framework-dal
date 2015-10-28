package com.jdk2010.framework.dal.cache;

public interface CacheProvider {

    int getSize(String cacheName);

    void put(String cacheName, Object key, Object value, int timeout);

    void put(Object key, Object value);
    
    void put(Object key, Object value,int timeout);

    Object get(String cacheName, Object key);

    Object get(Object value);

    void remove(String cacheName, Object key);

    void remove(Object key);

    void clear(String cacheName);

}
