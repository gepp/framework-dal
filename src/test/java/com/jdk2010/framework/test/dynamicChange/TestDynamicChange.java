package com.jdk2010.framework.test.dynamicChange;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.client.support.dynamicChange.DbContextHolder;
import com.jdk2010.framework.dal.client.support.dynamicChange.DynamicDataSource;
import com.jdk2010.framework.dal.transaction.ChainedTransactionManager;
import com.jdk2010.framework.test.dal.Student;
import com.jdk2010.framework.test.dal.TestDal;

import junit.framework.TestCase;

public class TestDynamicChange extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(TestDal.class);

  

    @Test
    public void testDynamicChangeInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("dynamicChange/applicationContext_dynamicChange.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        Student student = new Student();
        student.setName("111ykk");
        student.setAge("90");
        student.setId(86);
        DbContextHolder.setDataSourceName("dataSourceMysql1");
        int result = dalClient.save(student);
        logger.info("·µ»Øid£º" + result + "");

        Student student1 = new Student();
        student1.setName("111ykk");
        student1.setAge("90");
        student1.setId(87);
        DbContextHolder.setDataSourceName("dataSourceMysql1");
        result = dalClient.save(student1);
        logger.info("·µ»Øid£º" + result + "");

        Student student2 = new Student();
        student2.setName("111ykk");
        student2.setAge("90");
        student2.setId(88);
        DbContextHolder.setDataSourceName("dataSourceMysql3");
        result = dalClient.save(student2);
        logger.info("·µ»Øid£º" + result + "");
    }


}
