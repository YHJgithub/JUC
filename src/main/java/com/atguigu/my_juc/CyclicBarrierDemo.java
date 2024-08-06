package com.atguigu.my_juc;

import java.util.concurrent.CyclicBarrier;

/**
 * 使用 CyclicBarrier 实现召唤神龙案例
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier summonTheDragon = new CyclicBarrier(7, () -> {
            System.out.println("出来吧神龙，帮我实现愿望");
        });

        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                System.out.println("得到了" + Thread.currentThread().getName() + "星龙珠");
                try {
                    summonTheDragon.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i + 1)).start();
        }
    }
}
