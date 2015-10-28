package com.jdk2010.framework.dal.client.support;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class DefaultRouter implements InitializingBean {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<Class<?>, BaseRouterStrategy> routers;

    public Map<Class<?>, BaseRouterStrategy> getRouters() {
        return routers;
    }

    public void setRouters(Map<Class<?>, BaseRouterStrategy> routers) {
        this.routers = routers;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (routers.size() > 0) {
            logger.info("初始化分表策略......");
            for (Class clazz : routers.keySet()) {
                BaseRouterStrategy strategy = routers.get(clazz);
                logger.info("table-->" + strategy.getTableName() + "分表策略初始化成功");
            }

        }
    }

}
