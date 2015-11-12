package com.jdk2010.framework.dal.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class AbstractCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
    private Set<String> cacheNames = new LinkedHashSet<String>();

    protected final void addCache(String cacheName, Cache cache) {
        this.cacheMap.putIfAbsent(cacheName, cache);
        this.cacheNames.add(cacheName);
    }

    @Override
    public Cache getCache(String name) {
        return this.cacheMap.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(this.cacheNames);
    }

    // protected abstract Collection<? extends Cache> addCaches();
}
