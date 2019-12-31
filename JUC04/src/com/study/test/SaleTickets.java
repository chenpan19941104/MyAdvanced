package com.study.test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int num=30;
    private Lock lock=new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t"+"卖出第"+(num--)+"还剩下"+num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @author cp
 * @create 2019-12-17 10:57
 */
public class SaleTickets {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ExecutorService es= Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i <=30 ; i++) {
                es.execute(()->{ticket.sale();});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            es.shutdown();
        }
    }
}
