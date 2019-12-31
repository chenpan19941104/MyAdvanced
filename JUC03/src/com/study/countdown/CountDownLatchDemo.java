package com.study.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author cp
 * @create 2019-12-16 20:03
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t"+"离开教室");
                count.countDown();
            }, String.valueOf(i)).start();
        }
        count.await();
        System.out.println("教室无人");
    }
}
