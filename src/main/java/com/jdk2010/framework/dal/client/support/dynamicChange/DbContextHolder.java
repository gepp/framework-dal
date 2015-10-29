package com.jdk2010.framework.dal.client.support.dynamicChange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbContextHolder {
	
	private static final Logger logger=LoggerFactory.getLogger(DbContextHolder.class);
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSourceName(String dataSourceName) {
		contextHolder.set(dataSourceName);
	}

	public static String getDataSourceName() {
		String dataSourceName = contextHolder.get();
		if ("".equals(dataSourceName) || dataSourceName == null) {
			dataSourceName = "";
		}
		return dataSourceName;
	}

	public static void clearDataSource() {
        logger.info("当前数据源切换为默认数据源...");
		contextHolder.remove();
	}

}
