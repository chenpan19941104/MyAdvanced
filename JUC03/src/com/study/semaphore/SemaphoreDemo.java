package com.study.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author cp
 * @create 2019-12-16 20:37
 * 适用场景：多个线程抢多个资源
 * 在信号量上我们定义两种操作：
 * acquire(获取)当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1）
 *      要么一直等下去，直到有线程释放信号量或超时
 * release(释放)实际上会将信号量加1，然后呼唤等待的线程
 *
 * 信号量主要用于两个目的：一个是用于多个共享资源的互斥使用，另一个用户并发线程数的控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);//模拟有3个车位

        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                boolean flag=false;
                try {
                    semaphore.acquire();//模拟有6部汽车
                    flag=true;
                    System.out.println(Thread.currentThread().getName()+"\t"+"抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t"+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(flag){
                        semaphore.release();
                    }
                }
            }, String.valueOf(i)).start();
        }
    }
}
