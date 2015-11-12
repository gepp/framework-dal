package com.jdk2010.framework.dal.cache.support.ehcache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.jdk2010.framework.dal.cache.AbstractCacheManager;
import com.jdk2010.framework.dal.cache.Cache;

public class EhCacheCacheManager extends AbstractCacheManager implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String cacheConfigLocation;

    private net.sf.ehcache.CacheManager cacheManager;

    public String getCacheConfigLocation() {
        return cacheConfigLocation;
    }

    public void setCacheConfigLocation(String cacheConfigLocation) {
        this.cacheConfigLocation = cacheConfigLocation;
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = super.getCache(name);
        if (cache == null) {
            Ehcache ehcache = this.cacheManager.getEhcache(name);
            if (ehcache != null) {
                addCache(name, new EhCacheCache(ehcache));
                cache = super.getCache(name);
            }
        }
        return cache;
    }

    private CacheManager getDefaultCacheManager(){
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
        if(cacheConfigLocation==null){
              logger.info("未配置cacheConfigLocation,使用默认的cache");
              cacheManager=getDefaultCacheManager();
              return ;
        }
        String  ehcacheXmlPath = getClass().getResource("/").getFile();
         cacheManager = new CacheManager(ehcacheXmlPath+cacheConfigLocation);
        if (cacheManager == null ) {
            logger.info("配置文件不正确,使用默认的cache");
            cacheManager=getDefaultCacheManager();
        } else {
            logger.info("cache初始化成功......");
        }
    }

}
