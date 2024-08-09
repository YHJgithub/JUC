package com.atguigu.my_juc;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        System.out.println(new Date());

        try {
            for (int i = 0; i < 10; i++) {
                // 延迟执行
                executorService.schedule(() -> {
                    System.out.println(Thread.currentThread().getName() + " 定时任务被执行" + new Date());
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, 5, TimeUnit.SECONDS);
            }
        } finally {
            executorService.shutdown();
        }
    }
}
