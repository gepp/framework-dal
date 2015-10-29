package com.jdk2010.framework.test.router;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.client.support.DefaultDalClient;
import com.jdk2010.framework.dal.client.support.router.RouterManager;
import com.jdk2010.framework.util.DbKit;

public class TestRouter {
    public static void main(String[] args) {
        testDefaultRouter();
    }

    public static void testDefaultRouter() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient client = (DefaultDalClient) factory.getBean("mySqlDal");
        for (int i = 0; i < 100; i++) {
            Student student = new Student();
            student.setId(Long.parseLong(i + ""));
            student.setName("student" + i);
            student.setAge(i + "");
            client.save(student);
        }

    }

    public static void testUserDefineRouter() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient client = (DefaultDalClient) factory.getBean("mySqlDal");
        RouterManager.getRouters().put("userDefine", new DalUserDefine());
        StudentUserDefine student = new StudentUserDefine();
        student.setId(5L);
        student.setName("5016");
        System.out.println(DbKit.getTableName(student));
    }
}
