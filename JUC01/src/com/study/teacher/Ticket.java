package com.study.teacher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
    private Integer nums=30;
    /*public synchronized void saleTicket(){
        if(nums>0){
            System.out.println(Thread.currentThread().getName()
                    +"卖出第"+(nums--)+"张票,还剩"+nums+"张票");
        }
    }*/

    private Lock lock=new ReentrantLock();
    public void saleTicket(){
        lock.lock();
        try {
            if(nums>0){
                System.out.println(Thread.currentThread().getName()
                        +"卖出第"+(nums--)+"张票,还剩"+nums+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
