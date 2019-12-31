package com.study.waitandnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程，变量的初始值为0
 * 一个线程加1，一个线程减1
 * 交替10轮，最后变量的值不变
 *
 * 线程口诀：
 * 1.高内聚/低耦合的情况下，线程 操作 资源类
 * 2.判断/干活/通知
 * 3.防止多线程通信时,虚假唤醒的bug,在wait用while
 */
class AirCondition{//资源类
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public  void increment() throws InterruptedException {
        try {
            lock.lock();
//        if(number!=0){
            //判断
            while(number!=0){
                condition.await();//this.wait();
            }
            //干活
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();//this.notifyAll();//加了之后需要唤醒其他等待的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void decrement() throws InterruptedException {
        lock.lock();
        try {
//        if(number==0){
            while (number==0){
                condition.await();//this.wait();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();//this.notifyAll();//减了之后需要唤醒其他等待的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
   /* public synchronized void increment() throws InterruptedException {
//        if(number!=0){
        while(number!=0){
            this.wait();
        }
        ++number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();//加了之后需要唤醒其他等待的线程
    }
    public synchronized  void decrement() throws InterruptedException {
//        if(number==0){
        while (number==0){
            this.wait();
        }
        --number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();//减了之后需要唤醒其他等待的线程
    }*/
}
public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        AirCondition air = new AirCondition();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    air.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

            new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    air.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    air.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    air.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
