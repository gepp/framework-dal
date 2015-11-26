package com.jdk2010.framework.dal.cache.support.redis;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.jdk2010.framework.dal.cache.Cache;
import com.jdk2010.framework.dal.cache.exception.CacheException;

public class RedisCache implements Cache {
   
    private final Jedis jedis;
    
    private final String name;

    public RedisCache(String name, Jedis jedis) {

        if (jedis == null) {
            throw new CacheException("jedis is null");
        }
        this.name = name;
        this.jedis = jedis;
    }

    public RedisCache(String name, JedisPool jedisPool) {
        if (jedisPool == null) {
            throw new CacheException("jedisPool is null");
        }
        this.name = name;
        this.jedis = jedisPool.getResource();
    }

    @Override
    public String getName() throws CacheException {
        return name;
    }

    @Override
    public Object get(Object key) throws CacheException {

        if (key == null) {
            return null;
        } else {
            String str = jedis.get(key.toString());
            if (str == null) {
                return null;
            } else {
                return str;
            }
        }
    }

    @Override
    public void put(Object key, Object value) throws CacheException {

        if (key == null || value == null) {
            throw new CacheException("error key / value");
        }

        jedis.set(key.toString(), value.toString());
    }

    @Override
    public void evict(Object key) throws CacheException {

        if (key == null) {
            throw new CacheException("key is null");
        }
        Long ret = jedis.del(key.toString());
    }

    @Override
    public void clear() throws CacheException {
        jedis.flushAll();
    }

    @Override
    public Set<Object> keys() throws CacheException {
        Set<String> keys = jedis.keys("*");
        if (!(keys == null || keys.isEmpty())) {
            return Collections.unmodifiableSet(new LinkedHashSet<Object>(keys));
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public long size() throws CacheException {
        return jedis.dbSize();
    }

}
