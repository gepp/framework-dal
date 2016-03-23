package com.jdk2010.framework.test.dal;

import java.util.HashSet;
import java.util.Set;

import com.jdk2010.framework.dal.id.IdWorker1;

public class IdWorker1Test {
    static class IdWorkThreadTest implements Runnable {

        private Set<Long> set;
        private IdWorker1 idWorker;

        public IdWorkThreadTest(Set<Long> set, IdWorker1 idWorker) {
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
        Set<Long> set = new HashSet<Long>();
        final IdWorker1 work1 = new IdWorker1(1);
        final IdWorker1 work2 =  new IdWorker1(2);
        final IdWorker1 work3 =  new IdWorker1(3);
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("idwork1:"+set.size());
    }
}
