package com.study.define;

import java.util.concurrent.*;

/**
 * @author cp
 * @create 2019-12-17 15:14
 * 1.Arrays
 * 2.Collections
 * 3.Executors
 */
public class MyThreadpoolDemo {
    public static void main(String[] args) {
        ExecutorService threadpool = Executors.newFixedThreadPool(5);
        ExecutorService t2 = Executors.newSingleThreadExecutor();
        ExecutorService t1=Executors.newCachedThreadPool();

        ExecutorService myThreadpool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
//        new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            //定义的最大可用线程数为8，即需要<=8
            for (int i = 1; i <= 10; i++) {
                final int tempI=i;
                myThreadpool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t" + "办理业务"+tempI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            myThreadpool.shutdown();
        }
    }
}
