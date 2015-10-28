package com.jdk2010.framework.dal.client.support;

public abstract class BaseRouterStrategy {

    public String className;

    public String tableName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public abstract String getTableName();

}
