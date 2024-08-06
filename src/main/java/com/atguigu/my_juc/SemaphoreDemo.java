package com.atguigu.my_juc;

import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore控制并发访问
 * 
 * 场景：多辆车抢3个车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore parkingSpace = new Semaphore(3);

        for (int i = 1; i <= 4; i++) {
            new Thread(() -> {
                try {
                    parkingSpace.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                    parkingSpace.release();
                }
            }, "车辆" + i).start();
        }
    }
}
