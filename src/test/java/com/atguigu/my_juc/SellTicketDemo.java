package com.atguigu.my_juc;

class Ticket {
    private int totalTickets = 50;

    public synchronized void sellTicket() {
        if (totalTickets > 0) {
            System.out.println(Thread.currentThread().getName() + " sold ticket number: " + totalTickets);
            totalTickets--;
        } else {
            System.out.println("No tickets left to sell.");
        }
    }
}

class TicketSeller extends Thread {
    private Ticket ticket;

    public TicketSeller(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            synchronized (ticket) {
                ticket.sellTicket();
            }
        }
    }
}

public class SellTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        TicketSeller seller1 = new TicketSeller(ticket);
        TicketSeller seller2 = new TicketSeller(ticket);
        TicketSeller seller3 = new TicketSeller(ticket);
        seller1.start();
        seller2.start();
        seller3.start();
    }
}