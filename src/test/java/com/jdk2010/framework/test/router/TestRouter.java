package com.jdk2010.framework.test.router;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.client.support.DefaultDalClient;
import com.jdk2010.framework.dal.client.support.router.RouterManager;
import com.jdk2010.framework.test.router.custom.StudentRuleCustom;
import com.jdk2010.framework.util.DbKit;

public class TestRouter extends TestCase{
   
    public static void main(String[] args) {
        new TestRouter().testModRouter();
    }
    
    @Test
    public  void testModRouter() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient client = (DefaultDalClient) factory.getBean("mySqlDal");
        for (int i = 1; i < 10; i++) {
            com.jdk2010.framework.test.router.mod.Student student = new com.jdk2010.framework.test.router.mod.Student();
            student.setId(i);
            student.setName("student" + i);
            student.setAge(i + "");
            client.save(student);
        }

    }
    
    @Test
    public static void testUserCustomRouter() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient client = (DefaultDalClient) factory.getBean("mySqlDal");
        RouterManager.getRouters().put("custom", new StudentRuleCustom());
        com.jdk2010.framework.test.router.custom.Student student = new com.jdk2010.framework.test.router.custom.Student();
        student.setId(5);
        student.setName("5016");
        System.out.println(DbKit.getTableName(student.getClass()));
    }
}
