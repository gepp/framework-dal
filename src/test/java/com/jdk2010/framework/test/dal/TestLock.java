package com.jdk2010.framework.test.dal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.jdk2010.framework.dal.client.DalClient;

public class TestLock {
	public static void main(String[] args) {
		final BeanFactory factory = new ClassPathXmlApplicationContext("dal/applicationContext.xml");
        final DalClient dalClient = factory.getBean(DalClient.class);
        new Thread(new Runnable() {
			@Override
			public void run() {
				 DataSourceTransactionManager manager = (DataSourceTransactionManager) factory
			                .getBean("transactionManager");
			        TransactionStatus status = manager.getTransaction(new DefaultTransactionDefinition());
			        try {
			            Integer age = dalClient.queryColumn("select age from student where id=1 for update", "age");
			            Integer ageInt = age + 1;
			            Thread.currentThread().sleep(3000000);
			            dalClient.update("update student set age=" + ageInt + " where id=1");
			            manager.commit(status);
			        } catch (Exception e) {
			        	e.printStackTrace();
			            manager.rollback(status);
			        }
			}
		}).start();
        
        new Thread(new Runnable() {
			@Override
			public void run() {
			            dalClient.update("update student set age=50 where id=1");
			}
		}).start();
       
        
	}
}
