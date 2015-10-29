package com.jdk2010.framework.dal.client.support.router;

public interface BaseRouterStrategy {
    String getTableName(Object obj, String key, Integer count);
}
