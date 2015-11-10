package com.jdk2010.framework.dal.cache.support;

import com.jdk2010.framework.dal.cache.CacheProvider;

public class RedisProvider implements CacheProvider{

    @Override
    public int getSize(String cacheName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void put(String cacheName, Object key, Object value, int timeout) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void put(Object key, Object value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void put(Object key, Object value, int timeout) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object get(String cacheName, Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object get(Object value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(String cacheName, Object key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(Object key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clear(String cacheName) {
        // TODO Auto-generated method stub
        
    }

}
