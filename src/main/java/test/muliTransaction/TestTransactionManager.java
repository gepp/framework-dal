package test.muliTransaction;

import java.util.UUID;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.client.support.DefaultDalClient;
import com.jdk2010.framework.dal.client.support.dynamicChange.DbContextHolder;
import com.jdk2010.framework.dal.client.support.shard.DefaultShardingDalClient;
import com.jdk2010.framework.dal.transaction.ChainedTransactionManager;
import com.jdk2010.framework.util.DbKit;

public class TestTransactionManager {

    public static void main(String[] args) {
        test4();
    }

    public static void test1() {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        DalClient client = (DefaultDalClient) factory.getBean("mySqlDal");
        ChainedTransactionManager manager = (ChainedTransactionManager) factory.getBean("transactionManager");
        TransactionStatus transactionStatus = manager.getTransaction(new DefaultTransactionDefinition());
        try {
            DbContextHolder.setDataSourceName("dataSourceMysql1");
            client.update("insert into t_user(id,name) values ('" + UUID.randomUUID().toString() + "','3')");
            DbContextHolder.setDataSourceName("dataSourceMysql2");
            client.update("insert into t_user(id,name) values ('" + UUID.randomUUID().toString() + "','3')");
            DbContextHolder.setDataSourceName("dataSourceMysql3");
            client.update("insert into t_user(id,name) values ('" + UUID.randomUUID().toString() + "','3')");
            // manager.commit(transactionStatus);
        } catch (Exception e) {
            // manager.rollback(transactionStatus);
            System.out.println("回滚");
        }

    }

    public static void test2() {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext_shard.xml");
        DalClient client = (DefaultShardingDalClient) factory.getBean("myShardSqlDal");
        String id = UUID.randomUUID().toString();
        String sql = "insert into t_user(id,name) values ('" + id + "','3')";
        DbKit dbkit = new DbKit(sql);
        dbkit.put("id", "3");
        client.update(dbkit);
    }

    public static void test3() {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext_shard.xml");
        DalClient client = (DefaultShardingDalClient) factory.getBean("myShardSqlDal");
        ChainedTransactionManager manager = (ChainedTransactionManager) factory.getBean("transactionManager");
        TransactionStatus transactionStatus = manager.getTransaction(new DefaultTransactionDefinition());
        try {
            String id1 = "1";
            String id2 = "2";
            String id3 = "3";
            String id = "d223f182-1b4a-4863-a6c2-06a8676fd869";
            String sql = "insert into t_user(id,name) values ('" + id + "','3')";
            DbKit dbkit1 = new DbKit(sql);
            dbkit1.put("id", id1);
            client.update(dbkit1);
            DbKit dbkit2 = new DbKit(sql);
            dbkit2.put("id", id2);
            client.update(dbkit2);
            DbKit dbkit3 = new DbKit(sql);
            dbkit3.put("id", id3);
            client.update(dbkit3);
            manager.commit(transactionStatus);
        } catch (Exception e) {
            manager.rollback(transactionStatus);
            System.out.println("回滚");
        }

    }
    /**
     * 测试分库
     */
    public static void test4(){
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext_shard.xml");
        DalClient client = (DefaultShardingDalClient) factory.getBean("MyShardingDalClient");
        String sql="insert into t_user(id,name) values ('" + UUID.randomUUID().toString() + "','3')";
        DbKit dbKit=new DbKit(sql);
        dbKit.put("id","1");
        client.update(dbKit);
    }

}
