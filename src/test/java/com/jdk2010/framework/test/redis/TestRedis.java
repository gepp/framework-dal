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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.jdk2010.framework.dal.cache.support.redis.RedisCacheManager;
import com.jdk2010.framework.dal.client.DalClient;

public class TestRedis extends TestCase {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int threadTotal=1000;
    
    private static final int totalCount=30000;
    
    public static void main(String[] args) throws InterruptedException {
//        new TestRedis().testRedisThreadUpdate();
//        new TestRedis().testThreadUpdate();
        testCache();
    }

    @Test
    public static void testCache() {
        BeanFactory factory = new ClassPathXmlApplicationContext("redis/applicationContext.xml");
        RedisCacheManager redisCacheManager = (RedisCacheManager) factory.getBean("redisCacheManager");
       for(int i=0;i<100;i++)
        redisCacheManager.getRedis().incr("a");
    }

    @Test
    public void testRedisThreadUpdate() throws InterruptedException {
        long startTime=System.currentTimeMillis();
        BeanFactory factory = new ClassPathXmlApplicationContext("redis/applicationContext.xml");
        final RedisCacheManager redisCacheManager = (RedisCacheManager) factory.getBean("redisCacheManager");
        final JedisPool jedisPool = redisCacheManager.getJedisPool();
        redisCacheManager.getRedis().set("quantity", 0+"");
        final Map<String, Object> map = new HashMap<String, Object>();
        final ExecutorService service = Executors.newFixedThreadPool(threadTotal);
        for (int i = 0; i < totalCount; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    int threadCount = ((ThreadPoolExecutor) service).getActiveCount();
                    Jedis jedis = jedisPool.getResource();
                    Long a = jedis.incr("quantity");
                   // logger.info("线程" + a + "初始化，当前总线程" + threadCount);
                    map.put(a + "", a);
                    jedis.close();
                }
            });

        }
        service.shutdown();

        while (true) {
            int threadCount = ((ThreadPoolExecutor) service).getActiveCount();
            //logger.info("当前线程池大小:" + threadCount);
            Thread.sleep(5);
            if (threadCount == 0) {
                logger.info("map.size():" + map.size());
                int last_quantity = Integer.parseInt(redisCacheManager.getRedis().get("quantity")
                        .toString());
                long end=System.currentTimeMillis();
                logger.info("最终结果" + last_quantity);
                System.out.println("redis耗时："+(end-startTime));
                break;
            }

        }

    }

    @Test
    public void testThreadUpdate() throws InterruptedException {
        long startTime=System.currentTimeMillis();
        final BeanFactory factory = new ClassPathXmlApplicationContext("dal/applicationContext.xml");
        final DalClient dalClient = factory.getBean(DalClient.class);
        ExecutorService service = Executors.newFixedThreadPool(threadTotal);
        for (int i = 0; i < totalCount; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    DataSourceTransactionManager manager = (DataSourceTransactionManager) factory
                            .getBean("transactionManager");
                    TransactionStatus status = manager.getTransaction(new DefaultTransactionDefinition());
                    try {

                        String age = dalClient.queryColumn("select age from student where id=1 for update", "age");
                        Integer ageInt = Integer.parseInt(age) + 1;
                        //logger.info(ageInt + "");
                        dalClient.update("update student set age=" + ageInt + " where id=1");
                        manager.commit(status);
                    } catch (Exception e) {
                        manager.rollback(status);
                    }
                }
            });

        }
        service.shutdown();
        while (true) {
            int threadCount = ((ThreadPoolExecutor) service).getActiveCount();
           // logger.info("当前线程池大小:" + threadCount);
            Thread.sleep(5);
            if (threadCount == 0) {
                long end=System.currentTimeMillis();
                System.out.println("mysql耗时："+(end-startTime));
                break;
            }

        }
    }
}
