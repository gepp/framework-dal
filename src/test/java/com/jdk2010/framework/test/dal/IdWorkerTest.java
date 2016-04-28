package com.jdk2010.framework.test.dal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.jdk2010.framework.dal.id.IdWorker;

public class IdWorkerTest {
    static class IdWorkThreadTest implements Runnable {

        private Set<Long> set;
        private IdWorker idWorker;

        public IdWorkThreadTest(Set<Long> set, IdWorker idWorker) {
            super();
            this.set = set;
            this.idWorker = idWorker;
        }

        @Override
        public void run() {
            while (true) {
                long id = 0;
                try {
                    id = idWorker.nextId();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (!set.add(id)) {
                    System.out.println("重复的ID是".concat(String.valueOf(id)));
                }
            }
        }
    }

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        Set<Long> set = new HashSet<Long>();
        final IdWorker work1 = IdWorker.getFlowIdWorkerInstance();
        final IdWorker work2 = IdWorker.getFlowIdWorkerInstance();
        final IdWorker work3 =  IdWorker.getFlowIdWorkerInstance();
        Thread thread1 = new Thread(new IdWorkThreadTest(set,work1));
        Thread thread2 = new Thread(new IdWorkThreadTest(set,work2));
        Thread thread3 = new Thread(new IdWorkThreadTest(set,work3));
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread3.setDaemon(true);
        thread1.start();
//        thread2.start();
//        thread3.start();
        try {
            Thread.sleep(1000);
            System.out.println("Idworker2"+set.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-start);
//        for(Iterator<Long> it=set.iterator();it.hasNext();)
//        {
//            Long  lon=it.next();
//            System.out.println(lon);
//        }
    }
}
