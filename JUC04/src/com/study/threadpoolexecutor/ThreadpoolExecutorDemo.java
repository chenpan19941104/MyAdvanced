package com.study.threadpoolexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cp
 * @create 2019-12-17 18:42
 */
public class ThreadpoolExecutorDemo {
    public static void main(String[] args) {
//        ExecutorService threadpool= Executors.newFixedThreadPool(5);//固定了5个线程
//        ExecutorService threadpool= Executors.newSingleThreadExecutor();//仅一个线程  类似于加锁
        ExecutorService threadpool = Executors.newCachedThreadPool();//N个线程 自动扩容

        try {
            for (int i = 1; i <= 15; i++) {//模拟有15个人办理业务
                final int tempI = i;
                threadpool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务员" + tempI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadpool.shutdown();
        }
    }
}
