package com.study.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author cp
 * @create 2019-12-16 20:15
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier barrier=new CyclicBarrier(7,()->{
            System.out.println("集齐7颗龙珠召唤神龙");
        });
        for (int i = 1; i <=7 ; i++) {
            new Thread(() -> {
                try {
                    System.out.println("这是第"+Thread.currentThread().getName()+"颗龙珠...");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
