package com.jdk2010.framework.test.ehcache;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdk2010.framework.dal.cache.support.ehcache.EhCacheCacheManager;

public class TestEhcache extends TestCase {
    public static void main(String[] args) {
        new TestEhcache().testCache();
    }

    @Test
    public void testCache() {
        BeanFactory factory = new ClassPathXmlApplicationContext("ehCache/applicationContext.xml");
        EhCacheCacheManager ehCacheCacheManager = (EhCacheCacheManager) factory.getBean("ehCacheCacheManager");
        ehCacheCacheManager.getEhCache("metaCache").put("aaa", "bbb");
        System.out.println(ehCacheCacheManager.getEhCache("metaCache").get("aaa"));

    }
}
