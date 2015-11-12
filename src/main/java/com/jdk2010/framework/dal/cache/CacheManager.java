package com.jdk2010.framework.dal.cache;

import java.util.Collection;

/**
 * 缓存管理器（各不同的缓存框架实现各自的CacheManager）
 * 
 */
public interface CacheManager {

    /**
     * 获得缓存
     * 
     * @param name
     * @return
     */
    Cache getCache(String name);

    /**
     * 获得缓存的所有名称
     * 
     * @return
     */
    Collection<String> getCacheNames();

}
