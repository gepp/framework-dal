package com.jdk2010.framework.test.dal;

import java.util.ArrayList;
import java.util.Date;
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
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.JsonUtil;

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
            String sql="insert into product (product_name,product_store,brand_id,price,up_time,star,is_up) values ('"+name+"',"+store+","+brandid+","+price+","+new Date()+","+brandid+","+random.nextInt(2)+")";
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
            map.put("up_time",new Date());
            map.put("star",random.nextInt(10000));
            map.put("is_up",random.nextInt(2));
            paramMap.add(map);
        }
        String sql="insert into product (product_name,product_store,brand_id,price,up_time,star,is_up) values (:name,:store,:brandid,:price,:up_time,:star,:is_up)";
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
                        System.out.println("ageInt:"+ageInt);
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
    
    public void testBatchInvoice() {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        
        String sql1="INSERT INTO t_invoice(old_store_code,hx_return_kplsh,fpdm,last_accept_date,sale_org_name,thpcs,common_industry_code,ctime,backup_field_sec,invoice_ticket_code,sap_order_id,member_id,b2corderid,posorderid,tax_org_code,czdm,mobphonenum,maker_taxpayerno,twodimensional_code_info,security_code,accepter_invoice_name,emailinvoice,orderitemid,ordertime,invoices_printlog,fphm,inv_desc,bfthbs,old_orderitemid,old_pos_order_id,order_type,status,zkjehj,invoice_type,                          common_industry_name,kprq,store_code,pdf_file,ballot_code,old_sale_org,busines_platform_code,sf_bk,znxbj,backup_field_fir,member_name,dist_channel,fpqqlsh,pdf_url,sale_org,kpjehj,tschbz,accept_time,inv_status,invoicetitle) values" +
                "(:old_store_code,:hx_return_kplsh,:fpdm,:last_accept_date,:sale_org_name,:thpcs,:common_industry_code,:ctime,:backup_field_sec,:invoice_ticket_code,:sap_order_id,:member_id,:b2corderid,:posorderid,:tax_org_code,:czdm,:mobphonenum,:maker_taxpayerno,:twodimensional_code_info,:security_code,:accepter_invoice_name,:emailinvoice,:orderitemid,:ordertime,:invoices_printlog,:fphm,:inv_desc,:bfthbs,:old_orderitemid,:old_pos_order_id,:order_type,:status,:zkjehj,:invoice_type,:common_industry_name,:kprq,:store_code,:pdf_file,:ballot_code,:old_sale_org,:busines_platform_code,:sf_bk,:znxbj,:backup_field_fir,:member_name,:dist_channel,:fpqqlsh,:pdf_url,:sale_org,:kpjehj,:tschbz,:accept_time,:inv_status,:invoicetitle)";
        String sql2="INSERT INTO t_invoice_item(order_item_type,ordertime,cmmdty_band,fphm,cmmdtycode,fpdm,dj,sl,ckjg,seqno,tax_rate,fpqqlsh,extend_type,sap_product_code,sale_org,je,is_extend_flag,ysfy,cmmdtyname,yhdzje,store_code) values(:order_item_type,:ordertime,:cmmdty_band,:fphm,:cmmdtycode,:fpdm,:dj,:sl,:ckjg,:seqno,:tax_rate,:fpqqlsh,:extend_type,:sap_product_code,:sale_org,:je,:is_extend_flag,:ysfy,:cmmdtyname,:yhdzje,:store_code)";
        
        int  lsh1=40002;
        for(int i=0;i<100;i++){
            long startTime=System.currentTimeMillis();
            List<Map<String,Object>> paramMap=new ArrayList<Map<String,Object>>();
            for(int j=0;j<1000;j++){
                Map<String,Object> map=JsonUtil.jsonToMap("{\"id\":2759,\"fpqqlsh\":\"E104"+(lsh1+j )+"4701001\",\"orderitemid\":\"00014026192901\",\"posorderid\":\"E10463734\",\"b2corderid\":\"2002341018\",\"sap_order_id\":\"5000004831\",\"sale_org\":\"2700\",\"sale_org_name\":\"长春苏宁云商销售有限公司\",\"store_code\":\"7010\",\"old_orderitemid\":\"\",\"old_pos_order_id\":\"\",\"old_sale_org\":\"\",\"old_store_code\":\"\",\"ordertime\":\"2015-10-27 15:53:24\",\"znxbj\":\"1\",\"bfthbs\":\"\",\"thpcs\":\"\",\"invoices_printlog\":\"N\",\"tschbz\":\"0\",\"czdm\":\"10\",\"order_type\":\"ZOR\",\"dist_channel\":\"50\",\"verify_code\":\"4405\",\"sf_bk\":\"N\",\"fpdm\":\"151011420001\",\"fphm\":\"05029786\",\"invoice_type\":\"04\",\"invoicetitle\":\"孙磊\",\"kpjehj\":0.01,\"zkjehj\":-112.99,\"member_id\":\"6002035267\",\"member_name\":\"孙磊\",\"accepter_invoice_name\":\"孙磊\",\"mobphonenum\":\"18551603400\",\"emailinvoice\":\"\",\"common_industry_code\":\"\",\"common_industry_name\":\"\",\"maker_taxpayerno\":\"220104756197253\",\"tax_org_code\":\"\",\"busines_platform_code\":\"P0000001\",\"ballot_code\":\"\",\"status\":\"2\",\"twodimensional_code_info\":\"103E597610FAR1Q0eEO06Gg2Q000000109213XyWvx1Z19rsF23CL8993h4qW~10910421\",\"inv_status\":\"01\",\"inv_desc\":\"H00013472701001\",\"kprq\":\"2015-10-27 15:56:06\",\"hx_return_kplsh\":\"15101142000105029786\",\"kplx\":1,\"security_code\":\"0921E1F20E7B63049D76\",\"invoice_ticket_code\":\"21099\",\"last_accept_date\":\"2015-10-27 15:56:08\",\"accept_time\":1,\"pdf_url\":\"http://124.127.114.89:7009/51CLPro/eInvoiceDownload?fiscalCode=0921E1F20E7B63049D76\",\"pdf_file\":\"/notice/144593409385127924.pdf\",\"backup_field_fir\":\"\",\"backup_field_sec\":\"\",\"ctime\":\"2015-10-27 15:56:08\"}");
                paramMap.add(map);
            }
            lsh1=lsh1+(i+1)*1000;
            dalClient.batchUpdate(sql1, paramMap);
            long endTime=System.currentTimeMillis();
            long costTime=endTime-startTime;
            System.out.println("耗时:"+costTime);
        }
        
          
        
        
        int  lsh2=10002;
        Map<String,Object> mapitem=null;
        for(int i=0;i<100;i++){
            long startTime=System.currentTimeMillis();
            List<Map<String,Object>> paramEntityMap=new ArrayList<Map<String,Object>>();
            for(int j=0;j<1000;j++){
             mapitem=JsonUtil.jsonToMap("{\"id\":2732,\"fpqqlsh\":\"E104"+(lsh2+j )+"4701001\",\"sale_org\":\"2700\",\"store_code\":\"7281\",\"ordertime\":\"2016-02-18 09:55:45\",\"fpdm\":\"114011015609\",\"fphm\":\"12600001\",\"seqno\":\"\",\"cmmdtycode\":\"120877118\",\"cmmdtyname\":\"电子发票项目太原数据1\",\"sl\":1.000,\"dj\":56.00,\"je\":56.00,\"ysfy\":5.00,\"yhdzje\":0.00,\"ckjg\":56.00,\"tax_rate\":0.17,\"sap_product_code\":\"R0103002\",\"cmmdty_band\":\"000010254\",\"order_item_type\":\"10\",\"is_extend_flag\":\"\",\"extend_type\":\"\"}");
             paramEntityMap.add(mapitem);
            } 
            lsh2=lsh2+(i+1)*1000;
            dalClient.batchUpdate(sql2, paramEntityMap);
            long endTime=System.currentTimeMillis();
            long costTime=endTime-startTime;
            System.out.println("耗时:"+costTime);
        }
    }
    
    
    public static void main(String[] args) {
        
//        new TestDal().testBatchInvoice();
          new TestDal().testBatchDalInsert();
    }

}
