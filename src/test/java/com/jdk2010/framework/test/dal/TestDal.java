package com.jdk2010.framework.test.dal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.jdk2010.framework.dal.client.DalClient;

public class TestDal extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(TestDal.class);

 
    @Test
    public void testBaseDalInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        int result = dalClient.update("insert into student0(name,age) values ('gpp','18')");
        logger.info("影响结果：" + result + "");
    }
    
    @Test
    public void testSingleDalInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        String[] names = { "2010秋冬装新款", "韩版", "中腰铅笔", "修身牛仔裤",
                "女靴裤", "小脚裤", "韩国", "必备韩版流行",
                "磨砂牛皮", "单扣收腰小西装套装", "美国苹果王/APPEAL", "运动套装",
                "运动套装", "高跟鞋", "爱制造秋款", "女童秋装连衣裙", "女士手提斜跨包",
                "伊泰莲娜", "时尚高级兔毛", "高档银丝设计", "时尚个性内裤", "七匹狼",
                "双排扣短外套", "正品南极人", "男士家居服套装" };
        Double[] prices={16.55d,17.55d,18.00d,25.00d,540.34d,234.89d};
        
        Integer[] stores={100,200,300,400};
        
        Integer[] brandids={1,2,3,4};
        
        long start=System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            java.util.Random random=new java.util.Random();
            int nameRamdon1=random.nextInt(21);
            int nameRamdon2=random.nextInt(21);
            String name=names[nameRamdon1]+" "+names[nameRamdon2];
            int priceRandom=random.nextInt(6);
            Double price=prices[priceRandom];
            int storeRandom=random.nextInt(4);
            Integer store=stores[storeRandom];
            Integer brandid=brandids[storeRandom];
            String sql="insert into product (name,store,brand_id,price) values ('"+name+"',"+store+","+brandid+","+price+")";
            //System.out.println(sql);
            dalClient.update(sql);
        }
        long end=System.currentTimeMillis();
        long costTime=end-start;
        System.out.println("testSingleDalInsert 1000条记录耗时："+costTime+"ms");
        
       
    }
    
    @Test
    public void testBatchDalInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        for(int j=0;j<1000;j++){
        
        String[] names = { "2010秋冬装新款", "韩版", "中腰铅笔", "修身牛仔裤",
                "女靴裤", "小脚裤", "韩国", "必备韩版流行",
                "磨砂牛皮", "单扣收腰小西装套装", "美国苹果王/APPEAL", "运动套装",
                "运动套装", "高跟鞋", "爱制造秋款", "女童秋装连衣裙", "女士手提斜跨包",
                "伊泰莲娜", "时尚高级兔毛", "高档银丝设计", "时尚个性内裤", "七匹狼",
                "双排扣短外套", "正品南极人", "男士家居服套装" };
        Double[] prices={16.55d,17.55d,18.00d,25.00d,540.34d,234.89d};
        
        Integer[] stores={100,200,300,400};
        Integer[] brandids={1,2,3,4};
        
        long start=System.currentTimeMillis();
        List<Map<String,Object>> paramMap=new ArrayList<Map<String,Object>>();
        for(int i=0;i<1000;i++){
            Map<String,Object> map=new HashMap<String, Object>();
            java.util.Random random=new java.util.Random();
            int nameRamdon1=random.nextInt(21);
            int nameRamdon2=random.nextInt(21);
            String name=names[nameRamdon1]+" "+names[nameRamdon2];
            int priceRandom=random.nextInt(6);
            Double price=prices[priceRandom];
            int storeRandom=random.nextInt(4);
            Integer store=stores[storeRandom];
            Integer brandid=brandids[storeRandom];
            
            map.put("name", name);
            map.put("store", store);
            map.put("brandid", brandid);
            map.put("price", price);
            paramMap.add(map);
        }
        String sql="insert into product (name,store,brand_id,price) values (:name,:store,:brandid,:price)";
        dalClient.batchUpdate(sql, paramMap);
        long end=System.currentTimeMillis();
        long costTime=end-start;
        System.out.println("testBatchDalInsert 1000条记录耗时："+costTime+"ms");
        }
    }

    @Test
    public void testBaseDalModelInsert() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        Student student = new Student();
        student.setName("111ykk");
        student.setAge("90");
        int result = dalClient.save(student);
        logger.info("返回id：" + result + "");
    }

    @Test
    public void testUpdate() {
        BeanFactory factory = new ClassPathXmlApplicationContext("router/applicationContext_router.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        int result = dalClient.update("update student set age='20'");
        logger.info("影响结果：" + result + "");
    }

    @Test
    public void testThreadUpdate() {
        final BeanFactory factory = new ClassPathXmlApplicationContext("dal/applicationContext.xml");
        final DalClient dalClient = factory.getBean(DalClient.class);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    DataSourceTransactionManager manager = (DataSourceTransactionManager) factory
                            .getBean("transactionManager");
                    TransactionStatus status = manager.getTransaction(new DefaultTransactionDefinition());
                    try {

                        String age = dalClient.queryColumn("select age from student where id=1 for update", "age");
                        Integer ageInt = Integer.parseInt(age) + 1;
                        logger.info(ageInt + "");
                        dalClient.update("update student set age=" + ageInt + " where id=1");
                        manager.commit(status);
                    } catch (Exception e) {
                        manager.rollback(status);
                    }
                }
            });

        }
        service.shutdown();

    }

    @Test
    public void testReentrantLock() {
        final BeanFactory factory = new ClassPathXmlApplicationContext("dal/applicationContext.xml");
        final StudentDao studentDao=(StudentDao)factory.getBean("studentDao");
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                       String returnstr= studentDao.add();
                       
                       System.out.println(Thread.currentThread().getName()+"返回:"+returnstr);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName()+"中断");
                        //e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        service.shutdown();

    }
    public void testSybase(){
        final BeanFactory factory = new ClassPathXmlApplicationContext("dal/applicationContext.xml");
         DalClient sybaseClient =(DalClient)factory.getBean("sybaseDal");
         sybaseClient.update("select * from SKQ_BDC");
    }
    
    public static void main(String[] args) {
        //new TestDal().testSingleDalInsert();
        new TestDal().testBatchDalInsert();
    }

}
