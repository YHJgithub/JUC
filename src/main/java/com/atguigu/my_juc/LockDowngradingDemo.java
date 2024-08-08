package com.atguigu.my_juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDowngradingDemo {

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private int date;

    public void writeAfterRead(int data) {
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

        writeLock.lock();
        System.out.println("获取写锁");
        try {
            this.date = data;
            System.out.println("写操作完成");
            readLock.lock();
            System.out.println("获取读锁");
        } finally {
            writeLock.unlock();
            System.out.println("释放写锁");
        }
        
        try {
            System.out.println(this.date);
            System.out.println("读操作完成");
        } finally {
            readLock.unlock();
            System.out.println("释放读锁");
        }
    }

    public static void main(String[] args) {
        LockDowngradingDemo lockDowngradingDemo = new LockDowngradingDemo();
        lockDowngradingDemo.writeAfterRead(10);
    }
}
