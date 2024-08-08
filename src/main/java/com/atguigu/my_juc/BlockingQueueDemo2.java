package com.atguigu.my_juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo2 {

    public static void main(String[] args) {

        // 创建无界阻塞队列
        // BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();

        // 创建有界阻塞队列：当消费的进度较慢，生产进度较快，而且队列放不下的时候，生产会被自动阻塞，等待消费
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        // 生产者
        new Thread(() -> {

            try {
                for (int i = 1; i <= 10; i++) {

                    queue.put(i);
                    System.out.println("生产第" + i + "个馒头，" + "目前还剩" + queue.size() + "个馒头");
                    TimeUnit.SECONDS.sleep(1);

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "生产者").start();

        // 消费者
        new Thread(() -> {

            try {
                for (int i = 1; i <= 10; i++) {

                    System.out.println("消费第" + queue.take() + "个馒头" + "目前还剩" + queue.size() + "个馒头");
                    TimeUnit.SECONDS.sleep(3);

                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "消费者").start();
    }
}