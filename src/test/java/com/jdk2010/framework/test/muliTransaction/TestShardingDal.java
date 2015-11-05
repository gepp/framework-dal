package com.jdk2010.framework.test.muliTransaction;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.transaction.ChainedTransactionManager;
import com.jdk2010.framework.test.dal.Student;
import com.jdk2010.framework.test.dal.TestDal;

import junit.framework.TestCase;

public class TestShardingDal extends TestCase {

    private static Logger logger = LoggerFactory.getLogger(TestDal.class);

    @Test
    public void testInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("shard/applicationContext_shard.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        Student student = new Student();
        student.setName("111ykk");
        student.setAge("90");
        student.setId(81);
        int result = dalClient.save(student);
        logger.info("返回id：" + result + "");
    }

    @Test
    public void testMuliInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("shard/applicationContext_shard.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        Student student = new Student();
        student.setName("111ykk");
        student.setAge("90");
        student.setId(86);
        int result = dalClient.save(student);
        logger.info("返回id：" + result + "");

        Student student1 = new Student();
        student1.setName("111ykk");
        student1.setAge("90");
        student1.setId(87);
        result = dalClient.save(student1);
        logger.info("返回id：" + result + "");

        Student student2 = new Student();
        student2.setName("111ykk");
        student2.setAge("90");
        student2.setId(88);
        result = dalClient.save(student2);
        logger.info("返回id：" + result + "");
    }

    @Test
    public void testMuliInsertTransaction() {
        BeanFactory factory = new ClassPathXmlApplicationContext("shard/applicationContext_shard.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        ChainedTransactionManager manager = (ChainedTransactionManager) factory.getBean("transactionManager");
        TransactionStatus transactionStatus = manager.getTransaction(new DefaultTransactionDefinition());
        try {
            Student student = new Student();
            student.setId(1);
            student.setName("111ykk");
            student.setAge("90");
            int result = dalClient.save(student);
            logger.info("返回id：" + result + "");

            Student student1 = new Student();
            student1.setId(1);
            student1.setName("111ykk");
            student1.setAge("90");
            result = dalClient.save(student1);
            logger.info("返回id：" + result + "");

            Student student2 = new Student();
            student2.setId(1);
            student2.setName("111ykk");
            student2.setAge("90");
            
            result = dalClient.save(student2);
            logger.info("返回id：" + result + "");
            manager.commit(transactionStatus);
        } catch (Exception e) {
            e.printStackTrace();
            manager.rollback(transactionStatus);
        }
    }

}
