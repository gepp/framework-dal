package com.jdk2010.framework.dal.cache.support.redis;

import redis.clients.jedis.Jedis;

public interface RedisCallback<T> {
    public T call(Jedis jedis, Object params);
}
