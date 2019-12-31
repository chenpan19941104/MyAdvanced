package com.study.threadpoolexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tick{
    private int num=30;
    private Lock lock=new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"卖出第"+(num--)+"张票，还剩下"+num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @author cp
 * @create 2019-12-17 18:49
 */
public class SaleTickets {
    public static void main(String[] args) {
        Tick tick = new Tick();
        ExecutorService threadpool= Executors.newFixedThreadPool(3);
        for (int i = 1; i <=30 ; i++) {
            threadpool.execute(()->{
                tick.sale();
            });
        }
    }
}
