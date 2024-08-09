package com.atguigu.my_juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 这是一个演示类，展示了在 Java 中使用 Executors 的用法。
 */
public class ExecutorsDemo {
    /**
     * 主方法，演示如何向单线程执行器提交任务。
     *
     * @param args 命令行参数
     * @throws ExecutionException   如果计算抛出异常
     * @throws InterruptedException 如果在等待时当前线程被中断
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个单线程执行器
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 提交任务并获取一个 Future 对象，表示任务的待处理结果
        Future<String> future = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " 正在执行任务");
            return "atguigu";
        });
        // 获取任务完成时的结果
        String s = future.get();
        
        // 关闭线程执行器
        executorService.shutdown();
        
        // 打印结果以及执行该任务的线程
        System.out.println(Thread.currentThread().getName() + "," + s);
    }
}
