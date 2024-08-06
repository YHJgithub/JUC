package com.atguigu.my_juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("让你们好好尝尝唐山烧烤👊👊👊");
        return "别打了别打了，我再也不吃烧烤了";
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        FutureTask<String> stringFutureTask = new FutureTask<>(new MyCallable());
        new Thread(stringFutureTask).start();
        try {
            System.out.println(stringFutureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
