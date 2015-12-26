package com.jdk2010.framework.dal.cache.support.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.util.StringUtil;

public class RedisCacheManager implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private JedisPool jedisPool;

    private String host;

    private String password;

    private int port;

    private int timeout;

    private JedisPoolConfig redisConfig;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public JedisPoolConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(JedisPoolConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    
    public RedisSimpleTempalte getRedis(){
        return new RedisSimpleTempalte(new RedisClient(jedisPool));
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtil.isBlank(host) || StringUtil.isBlank(port)) {
            logger.error("host/port is null");
            ExceptionUtil.throwException(new RuntimeException("host/port is null"));
        }
        jedisPool = new JedisPool(redisConfig, host, port, timeout);
        logger.info("redis init success......");
    }

}
