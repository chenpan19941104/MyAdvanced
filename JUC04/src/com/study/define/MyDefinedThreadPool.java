package com.study.define;

import java.util.concurrent.*;

/**
 * @author cp
 * @create 2019-12-17 19:31
 */
public class MyDefinedThreadPool {
    public static void main(String[] args) {
//        ExecutorService threadpool=Executors.newFixedThreadPool(5);
        ExecutorService threadpool=new ThreadPoolExecutor(
                2,
                5,//最大线程数为5
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),//阻塞队列的容量
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            //不得超过最大线程数+最大阻塞队列容量
            for (int i = 1; i <=8 ; i++) {
                final int tempI=i;
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务"+tempI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadpool.shutdown();
        }
    }
}
