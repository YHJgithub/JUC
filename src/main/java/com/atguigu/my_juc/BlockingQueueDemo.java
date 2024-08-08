package com.atguigu.my_juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

        // 会报错
        blockingQueue.add(1);
        Integer remove = blockingQueue.remove();
        Integer element = blockingQueue.element();

        // 返回特殊值 true/false
        blockingQueue.offer(1);
        // 返回被删除的值，如果没有返回null
        blockingQueue.poll();
        // 返回队首元素，如果没有返回null
        blockingQueue.peek();

        // 无法立即执行会阻塞
        blockingQueue.put(1);
        blockingQueue.take();

        // offer会返回 true/false
        blockingQueue.offer(1, 3, TimeUnit.SECONDS);
        // poll返回被删除的值，如果没有返回null
        blockingQueue.poll(3, TimeUnit.SECONDS);

    }
}
