package com.atguigu.my_juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试ReentrantLock的可重入性
 */
public class ReentrantLockDemo2 {
    private final ReentrantLock lock = new ReentrantLock();
    
    public void a(){
        lock.lock();
        this.b();
        System.out.println("a");
        lock.unlock();
    }
    
    public void b(){
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        new ReentrantLockDemo2().a();
    }
}
