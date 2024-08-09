package com.atguigu.my_juc;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledAtFixRateDemo {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        System.out.println(new Date());

        for (int i = 0; i < 10; i++) {
            // 延迟执行
            scheduledThreadPool.scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName() + " 定时任务被执行" + new Date());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, 5, 1, TimeUnit.SECONDS);
        }
    }
}
