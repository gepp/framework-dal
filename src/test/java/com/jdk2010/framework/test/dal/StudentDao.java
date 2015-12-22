package com.jdk2010.framework.test.dal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class StudentDao {

    private ReentrantLock lock = new ReentrantLock();

    public String add() throws InterruptedException {
        if (lock.tryLock(2, TimeUnit.SECONDS)) {
            System.out.println(Thread.currentThread().getName() + "耗时操作....");
            Thread.sleep(5000);
            lock.unlock();
            return "ok";
        } else {
            throw new InterruptedException("超时...");
        }
    }
}
