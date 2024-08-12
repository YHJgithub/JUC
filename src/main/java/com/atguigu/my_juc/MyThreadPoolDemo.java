package com.atguigu.my_juc;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <= 8; i++) {
                int tmpI = i;
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 处理业务" + "\t 顾客: " + tmpI);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
