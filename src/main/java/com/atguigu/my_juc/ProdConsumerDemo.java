package com.atguigu.my_juc;

import java.util.concurrent.TimeUnit;

/**
 * AirCondition类用于实现一个简单的生产者-消费者模型。
 * 这个类管理一个资源（即数字），允许生产者增加资源，
 * 允许消费者减少资源。使用synchronized确保线程安全。
 */
class AirCondition {
    // 当前资源数量
    int number = 0;

    /**
     * 增加资源的方法，生产者调用。
     * 如果当前资源数量不为0，生产者将等待。
     *
     * @throws InterruptedException 当线程被中断时抛出该异常
     */
    public synchronized void increment() throws InterruptedException {
        while (number != 0) {
            this.wait(); // 资源已满，生产者等待
        }
        // 生产资源
        System.out.println(Thread.currentThread().getName() + "\t" + (++number));
        // 通知消费者, 资源已增加
        this.notifyAll();
    }

    /**
     * 减少资源的方法，消费者调用。
     * 如果当前资源数量为0，消费者将等待。
     *
     * @throws InterruptedException 当线程被中断时抛出该异常
     */
    public synchronized void decrement() throws InterruptedException {
        while (number == 0) {
            this.wait(); // 资源为空，消费者等待
        }
        // 消费资源
        System.out.println(Thread.currentThread().getName() + "\t" + (--number));
        // 通知生产者, 资源已减少
        this.notifyAll();
    }
}

public class ProdConsumerDemo {
    public static void main(String[] args) {
        AirCondition ac = new AirCondition();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ac.increment();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ac.decrement();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ac.increment();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ac.decrement();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
