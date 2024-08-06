package com.atguigu.my_juc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NoSafeDemo {
    public static void main(String[] args) {
        // list();
        // set();
        map();
    }

    private static void map() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            }).start();
        }
    }

    private static void set() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                set.add(String.valueOf(UUID.randomUUID()).substring(0, 6));
                System.out.println(set);
            }).start();
        }
    }

    private static void list() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                list.add(String.valueOf(UUID.randomUUID()).substring(0, 6));
                System.out.println(list);
            }).start();
        }
    }
}

