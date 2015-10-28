package com.jdk2010.framework.dal.cache.support;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.jdk2010.framework.dal.cache.CacheProvider;

public class EhcacheProvider implements InitializingBean, CacheProvider {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Resource cacheConfigLocation;

	private CacheManager manager;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("===cache init===");
		this.manager = new CacheManager(this.cacheConfigLocation.getFile()
				.getAbsolutePath());
	}

	@Override
	public int getSize(String cacheName) {
		Cache cache = this.manager.getCache(cacheName);
		return cache.getSize();
	}

	@Override
	public void put(String cacheName, Object key, Object value, int timeout) {
		Cache cache = this.manager.getCache(cacheName);
		if (cache != null) {
			Element element = new Element(key, value);
			if (timeout != 0) {
				element.setTimeToLive(timeout);
			}
			cache.put(element);
		} else {
			logger.info("cacheName:" + cacheName + " is null");
		}
	}

	@Override
	public Object get(String cacheName, Object key) {
		Cache cache = this.manager.getCache(cacheName);
		if (cache != null) {
			Element e = this.manager.getCache(cacheName).get(key);
			if (e == null) {
				return null;
			} else {
				return e.getObjectValue();
			}
		} else {
			logger.info("cacheName:" + cacheName + " is null");
			return null;
		}

	}

	@Override
	public void remove(String cacheName, Object key) {
		Cache cache = this.manager.getCache(cacheName);
		if (cache != null) {
			cache.remove(key);
		} else {
			logger.info("cacheName:"+cacheName+"is null");
		}
	}

	@Override
	public void clear(String cacheName) {
		Cache cache = this.manager.getCache(cacheName);
		if (cache != null) {
			cache.removeAll();
		} else {
			logger.info("cacheName:"+cacheName+"is null");
		}	
	}

}
