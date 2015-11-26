package com.jdk2010.framework.dal.cache;

import java.util.Set;

import com.jdk2010.framework.dal.cache.exception.CacheException;

public interface Cache {

    /**
     * 返回缓存的名称
     * 
     * @return
     */
    String getName() throws CacheException;

    /**
     * 获得缓存值
     * 
     * @param key 缓存key
     * @return 缓存值
     */
    Object get(Object key) throws CacheException;

    /**
     * 设置缓存
     * 
     * @param key 缓存key
     * @param value 缓存值
     */
    void put(Object key, Object value) throws CacheException;

    /**
     * 回收缓存
     * 
     * @param key
     */
    void evict(Object key) throws CacheException;

    /**
     * 清空所有的缓存
     */
    void clear() throws CacheException;

    /**
     * 获得所有的key
     * 
     * @return key集合
     */
    Set<Object> keys() throws CacheException;

    /**
     * 获得缓存的key-value个数
     * 
     * @return key-value个数
     */
    long size() throws CacheException;

 
}
