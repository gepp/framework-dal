package com.jdk2010.framework.dal.cache.support.ehcache;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import com.jdk2010.framework.dal.cache.exception.CacheException;

public class EhCacheCache  {
    private final Ehcache ehcache;

    public EhCacheCache(Ehcache ehcache) {
        if (ehcache == null) {
            throw new CacheException("传入的ehcache为null");
        }
        this.ehcache = ehcache;
    }

    public String getName() throws CacheException {
        return this.ehcache.getName();
    }

    public Object get(Object key) throws CacheException {

        if (key == null) {
            return null;
        } else {
            Element element = ehcache.get(key);
            if (element == null) {
                return null;
            } else {
                return element.getObjectValue();
            }
        }

    }

    public void put(Object key, Object value) throws CacheException {
        Element element = new Element(key, value);
        ehcache.put(element);
    }
    
    //timeToLiveSeconds 最大存活时间
    //timeToIdleSeconds 最大访问间隔时间
    public void put(Object key, Object value,int timeout) throws CacheException {
        Element element = new Element(key, value);
        element.setTimeToLive(timeout);
        ehcache.put(element);
    }

    public void evict(Object key) throws CacheException {
        ehcache.remove(key);
    }

    public void clear() throws CacheException {
        ehcache.removeAll();
    }

    public Set<Object> keys() throws CacheException {
        @SuppressWarnings("unchecked")
        List<Object> keys = ehcache.getKeys();
        if (!(keys == null || keys.isEmpty())) {
            return Collections.unmodifiableSet(new LinkedHashSet<Object>(keys));
        } else {
            return Collections.emptySet();
        }
    }

    public long size() throws CacheException {
        return ehcache.getSize();
    }

  
}
