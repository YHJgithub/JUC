package com.atguigu.my_juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CASExample {
    // 定义一个原子整数，初始值为0
    private AtomicInteger atomicInt = new AtomicInteger(0);
    // 定义一个普通整数，初始值为0
    private int number = 0;

    // 定义一个方法，通过CAS操作实现原子递增
    public void increment() {
        int oldValue; // 保存当前值
        int newValue; // 保存新值（当前值加1）
        // 自旋操作：不断尝试直到CAS操作成功
        do {
            oldValue = atomicInt.get(); // 获取当前值
            newValue = oldValue + 1; // 计算新值
        } while (!atomicInt.compareAndSet(oldValue, newValue)); // 尝试更新，如果失败则重新尝试
    }

    // 获取当前的普通整数值
    public int getNumber() {
        return number;
    }

    // 设置当前的普通整数值
    public synchronized void setNumber() {
        number++;
    }


    // 获取当前的原子整数值
    public int getValue() {
        return atomicInt.get();
    }

    public static void main(String[] args) throws InterruptedException {
        // 定义一个CAS实例
        CASExample example = new CASExample();
        // 定义一个计数器
        CountDownLatch countDownLatch = new CountDownLatch(100);

        // 创建多个线程同时执行increment操作
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    example.increment();
                    example.setNumber();
                }
                countDownLatch.countDown();
            }).start();
        }

        // 等待线程执行完毕
        countDownLatch.await();

        // 输出最终结果
        System.out.println("Final value CAS: " + example.getValue());
        // 输出最终结果
        System.out.println("Final value普通变量 : " + example.getNumber());
    }
}