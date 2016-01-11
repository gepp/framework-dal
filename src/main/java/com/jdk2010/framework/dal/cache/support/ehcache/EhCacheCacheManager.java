package com.jdk2010.framework.dal.cache.support.ehcache;

import java.io.UnsupportedEncodingException;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class EhCacheCacheManager implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String cacheConfigLocation;

    private net.sf.ehcache.CacheManager cacheManager;

    public String getCacheConfigLocation() {
        return cacheConfigLocation;
    }

    public void setCacheConfigLocation(String cacheConfigLocation) {
        this.cacheConfigLocation = cacheConfigLocation;
    }

    public EhCacheCache getEhCache(String name) {
        EhCacheCache ehCache = new EhCacheCache(this.cacheManager.getEhcache(name));
        return ehCache;
    }

    private CacheManager getDefaultCacheManager() {
        net.sf.ehcache.config.Configuration config = new Configuration();
        // 如果不使用ehcache.xml配置文件，那么必须用代码配置一个defaultCacheConfiguration
        CacheConfiguration defaultCacheConfiguration = new CacheConfiguration();
        defaultCacheConfiguration.setMaxEntriesLocalHeap(0);
        defaultCacheConfiguration.setEternal(false);
        defaultCacheConfiguration.setTimeToIdleSeconds(30);
        defaultCacheConfiguration.setTimeToLiveSeconds(30);
        config.addDefaultCache(defaultCacheConfiguration);// 设置默认cache
        return CacheManager.create(config);
    }

    @Override
    public void afterPropertiesSet() {
        if (cacheConfigLocation == null) {
            logger.info("no config cacheConfigLocation,use default cache");
            cacheManager = getDefaultCacheManager();
            return;
        }
        String ehcacheXmlPath = getClass().getResource("/").getFile();
        try {
			ehcacheXmlPath=java.net.URLDecoder.decode(ehcacheXmlPath,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        cacheManager = new CacheManager(ehcacheXmlPath + cacheConfigLocation);
        if (cacheManager == null) {
            logger.info("config is error,use default cache");
            cacheManager = getDefaultCacheManager();
        } else {
            logger.info("cache init success......");
        }
    }

}
