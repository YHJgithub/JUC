package com.atguigu.my_juc;

class Ticket {
    private int totalTickets = 50; // 总票数
    private boolean soldOut = false; // 是否售罄的标志

    // 同步方法，售票
    public synchronized void sellTicket() {
        if (totalTickets > 0) {
            // 打印当前售票员销售的票号
            System.out.println(Thread.currentThread().getName() + " sold ticket number: " + totalTickets);
            totalTickets--; // 减少票数
        } else if (!soldOut) {
            // 打印票已售罄的信息，设置售罄标志
            System.out.println("No tickets left to sell.");
            soldOut = true; // 标记为售罄
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

class TicketSeller extends Thread {
    private Ticket ticket; // 售票员持有的票

    // 构造函数，初始化售票员
    public TicketSeller(Ticket ticket) {
        this.ticket = ticket;
    }

    // 线程执行的逻辑
    @Override
    public void run() {
        while (true) {
            synchronized (Object.class) { // 同步块，控制售票流程
                // 检查票是否已售罄
                if (ticket.getTotalTickets() <= 0 && ticket.isSoldOut()) {
                    break; // 如果票已售罄，退出循环
                }
                ticket.sellTicket(); // 调用售票方法
            }
        }
    }
}

public class SellTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(); // 创建票对象
        TicketSeller seller1 = new TicketSeller(ticket); // 创建售票员1
        TicketSeller seller2 = new TicketSeller(ticket); // 创建售票员2
        TicketSeller seller3 = new TicketSeller(ticket); // 创建售票员3

        // 启动售票员线程
        seller1.start();
        seller2.start();
        seller3.start();
    }
}