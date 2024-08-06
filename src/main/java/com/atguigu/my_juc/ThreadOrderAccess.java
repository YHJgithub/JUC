package com.atguigu.my_juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Printer {

    Lock lock = new ReentrantLock();
    int flag = 1;   // 1: print5  2: print10  3: print15

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();


    public void print5() {
        lock.lock();
        try {
            while (flag != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Make America Great Again");
                }
            } finally {
                flag = 2;
                condition2.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (flag != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("a tyrannical government will surely perish");
                }
            } finally {
                flag = 3;
                condition3.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (flag != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                for (int i = 0; i < 15; i++) {
                    System.out.println("Viva la libertad.");
                }
            } finally {
                flag = 1;
                condition1.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        Printer p = new Printer();
        for (int i = 0; i < 10; i++) {
            new Thread(p::print5).start();
            new Thread(p::print10).start();
            new Thread(p::print15).start();
        }
    }
}
