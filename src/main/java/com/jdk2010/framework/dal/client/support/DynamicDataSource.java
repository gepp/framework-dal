package com.jdk2010.framework.dal.client.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDataSourceName();
    }

}
