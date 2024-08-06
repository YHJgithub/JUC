package com.atguigu.my_juc;

// 引入必要的类

import java.util.concurrent.locks.ReentrantLock;

// 创建一个死锁示例类
public class DeadlockDemo2 {
    // 定义两个可重入锁
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        // 创建并启动线程1
        new Thread(() -> {
            // 尝试获取锁1
            boolean result1 = lock1.tryLock();
            if (result1) {
                try {
                    System.out.println("Thread 1: Holding lock 1...");
                    // 模拟一些工作
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 1: Waiting for lock 2...");
                    // 尝试获取锁2
                    boolean result2 = lock2.tryLock();
                    if (result2) {
                        try {
                            System.out.println("Thread 1: Both locks acquired.");
                        } finally {
                            // 释放锁2
                            lock2.unlock();
                        }
                    } else {
                        System.out.println("Thread 1: 未获取到锁2");
                    }
                } finally {
                    // 释放锁1
                    lock1.unlock();
                }
            } else {
                System.out.println("Thread 1: 未获取到锁1");
            }
        }).start();

        // 创建并启动线程2
        new Thread(() -> {
            // 尝试获取锁2
            boolean result2 = lock2.tryLock();
            if (result2) {
                try {
                    System.out.println("Thread 2: Holding lock 2...");
                    // 模拟一些工作
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 2: Waiting for lock 1...");
                    // 尝试获取锁1
                    boolean result1 = lock1.tryLock();
                    if (result1) {
                        try {
                            System.out.println("Thread 2: Both locks acquired.");
                        } finally {
                            // 释放锁1
                            lock1.unlock();
                        }
                    } else {
                        System.out.println("Thread 2: 未获取到锁1");
                    }
                } finally {
                    // 释放锁2
                    lock2.unlock();
                }
            } else {
                System.out.println("Thread 2: 未获取到锁2");
            }
        }).start();
    }
}