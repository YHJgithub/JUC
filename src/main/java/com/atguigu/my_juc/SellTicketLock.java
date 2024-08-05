package com.atguigu.my_juc;

import java.util.concurrent.locks.ReentrantLock;

class TicketLock {

    private final ReentrantLock lock = new ReentrantLock(true);

    private int totalTickets = 50; // 总票数
    private boolean soldOut = false; // 是否售罄的标志

    // 同步方法、售票
    public void sellTicket() {
        lock.lock();
        try {
            if (totalTickets > 0) {
                // 打印当前售票员销售的票号
                System.out.println(Thread.currentThread().getName() + " sold ticket number: " + totalTickets);
                totalTickets--; // 减少票数
            } else if (!soldOut) {
                // 打印票已售罄的信息，设置售罄标志
                System.out.println("No tickets left to sell.");
                soldOut = true; // 标记为售罄
            }
        } finally {
            lock.unlock();
        }
    }

    // 获取剩余票数
    public int getTotalTickets() {
        return totalTickets;
    }

    // 检查是否售罄
    public boolean isSoldOut() {
        return soldOut;
    }
}

public class SellTicketLock {
    public static void main(String[] args) {

        TicketLock ticketLock = new TicketLock();

        new Thread(() -> {
            while (true) {
                // 检查票是否已售罄
                if (ticketLock.getTotalTickets() <= 0 && ticketLock.isSoldOut()) {
                    break; // 如果票已售罄，退出循环
                }
                ticketLock.sellTicket(); // 调用售票方法
            }
        }, "A").start();

        new Thread(() -> {
            while (true) {
                // 检查票是否已售罄
                if (ticketLock.getTotalTickets() <= 0 && ticketLock.isSoldOut()) {
                    break; // 如果票已售罄，退出循环
                }
                ticketLock.sellTicket(); // 调用售票方法
            }
        }, "B").start();

        new Thread(() -> {
            while (true) {
                // 检查票是否已售罄
                if (ticketLock.getTotalTickets() <= 0 && ticketLock.isSoldOut()) {
                    break; // 如果票已售罄，退出循环
                }
                ticketLock.sellTicket(); // 调用售票方法
            }
        }, "C").start();
    }
}
