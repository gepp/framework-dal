package com.jdk2010.framework.test.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.jdk2010.framework.dal.cache.support.redis.RedisCacheManager;

public class TestRedis extends TestCase {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws InterruptedException {
        new TestRedis().testThreadUpdate();
    }

    @Test
    public void testCache() {
        BeanFactory factory = new ClassPathXmlApplicationContext("redis/applicationContext.xml");
        RedisCacheManager redisCacheManager = (RedisCacheManager) factory.getBean("redisCacheManager");
        JedisPool jedisPool = redisCacheManager.getJedisPool();
        jedisPool.getResource().incr("a");
    }

    @Test
    public void testThreadUpdate() throws InterruptedException {
        BeanFactory factory = new ClassPathXmlApplicationContext("redis/applicationContext.xml");
        final RedisCacheManager redisCacheManager = (RedisCacheManager) factory.getBean("redisCacheManager");
        final JedisPool jedisPool = redisCacheManager.getJedisPool();
        redisCacheManager.getCache("redisDefault").put("quantity", 0);
        final Map<String, Object> map = new HashMap<String, Object>();
        ExecutorService service = Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 20000; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = jedisPool.getResource();
                    Long a = jedis.incr("quantity");
                    logger.info(a+"");
                    map.put(a + "", a);
                    jedis.close();
                }
            });

        }
        service.shutdown();
     
        while (true) {
            int threadCount = ((ThreadPoolExecutor) service).getActiveCount();
            logger.info("当前线程池大小:" + threadCount);
            
            Thread.sleep(4000);
            if (threadCount == 0) {
                logger.info("map.size():" + map.size());
                int last_quantity = Integer.parseInt(redisCacheManager.getCache("redisDefault").get("quantity")
                        .toString());
                logger.info("最终结果" + last_quantity);
                break;
            }

        }

    }
}
