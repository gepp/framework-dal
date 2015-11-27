package com.jdk2010.framework.dal.cache.support.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.jdk2010.framework.dal.cache.exception.CacheException;

public class RedisClient {

    private final JedisPool pool;

    public RedisClient(JedisPool jedisPool) {
        if (jedisPool == null) {
            throw new CacheException("jedisPool is null");
        }
        this.pool = jedisPool;
    }

    public void destory() {
        pool.destroy();
    }

    public Jedis getRedis() {
        Jedis jedis = pool.getResource();
        jedis.select(0);
        return jedis;
    }

    public Jedis getRedis(int index) {
        Jedis jedis = pool.getResource();
        jedis.select(index);
        return jedis;
    }

    public void returnRedis(Jedis jedis) {
        pool.returnResource(jedis);
    }

    public void returnBrokeRedis(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }

}
