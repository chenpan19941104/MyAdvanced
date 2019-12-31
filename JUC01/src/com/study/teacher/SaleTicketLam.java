package com.study.teacher;

public class SaleTicketLam {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 1; i < 35; i++) ticket.saleTicket(); }, "A").start();
        new Thread(() -> { for (int i = 1; i < 35; i++) ticket.saleTicket(); }, "B").start();
        new Thread(() -> { for (int i = 1; i < 35; i++) ticket.saleTicket(); }, "C").start();

        new Thread(() -> { for (int i = 0; i < 35; i++) ticket.saleTicket(); },"D").start();
    }
}
