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
	public static void main(String[] args) throws InterruptedException {
	    DbContextHolder.setDataSourceName("aaa");
	    System.out.println("1."+DbContextHolder.getDataSourceName());
	    new Thread(new  Runnable() {
            
            @Override
            public void run() {
                DbContextHolder.setDataSourceName("bbb");
                System.out.println("2."+DbContextHolder.getDataSourceName());
            }
        }).start();
	    Thread.sleep(1000);
	    System.out.println("3."+DbContextHolder.getDataSourceName());
    }

}
