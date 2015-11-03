package com.jdk2010.framework.test.dal;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdk2010.framework.dal.client.DalClient;

public class TestDal extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(TestDal.class);
    
    @Test
    public void testBaseDalInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        int result = dalClient.update("insert into student0(name,age) values ('gpp','18')");
        logger.info("影响结果："+result+"");
    }

    @Test
    public void testBaseDalModelInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        Student student = new Student();
        student.setName("111ykk");
        student.setAge("90");
        int result=dalClient.save(student);
        logger.info("返回id："+result+"");
    }
    
    @Test
    public void testUpdate() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        int result = dalClient.update("update student set age='20'");
        logger.info("影响结果："+result+"");
    }

}
