package com.jdk2010.framework.test.ehcache;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdk2010.framework.dal.cache.support.EhcacheProvider;

public class TestEhcache extends TestCase {
    
    @Test
    public void testCache() {
        BeanFactory factory = new ClassPathXmlApplicationContext("ehCache/applicationContext.xml");
        EhcacheProvider ehcacheProvider = (EhcacheProvider) factory.getBean("ehcacheProvider");
        ehcacheProvider.put("metaCache", "key1", "abc", 5);
        System.out.println(ehcacheProvider.get("metaCache", "key1"));
        System.out.println("============");
        ehcacheProvider.put("key2", "def====");
        System.out.println(ehcacheProvider.get("key2"));

    }
}
