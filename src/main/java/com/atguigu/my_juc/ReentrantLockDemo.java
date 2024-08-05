package com.atguigu.my_juc;

/**
 * 测试synchronized可重入性
 */
public class ReentrantLockDemo {
    public synchronized void a() {
        this.b();
        System.out.println("a");
    }

    public synchronized void b() {
        System.out.println("b");
    }

    public static void main(String[] args) {
        new ReentrantLockDemo().a();
    }
}
