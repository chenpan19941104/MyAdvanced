package com.study.sale;

public class SaleTickets {
    public static void main(String[] args) {
        SaleThread thread1=new SaleThread();
        Thread t1=new Thread(thread1,"售票员1");
        t1.start();
        Thread t2=new Thread(thread1,"售票员2");
        t2.start();
        Thread t3=new Thread(thread1,"售票员3");
        t3.start();
    }
}


