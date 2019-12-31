package com.study.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author cp
 * @create 2019-12-17 18:02
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(3);
        /*
        //抛出异常
        System.out.println(bq.add("a"));
        System.out.println(bq.add("b"));
        System.out.println(bq.add("c"));
//      System.out.println(bq.add("d"));//java.lang.IllegalStateException: Queue full
        System.out.println(bq.element());//获取
        System.out.println(bq.remove());
        System.out.println(bq.add("d"));//移除*/

        //特殊值
       /* System.out.println(bq.offer("A"));
        System.out.println(bq.offer("B"));
        System.out.println(bq.offer("C"));
        //System.out.println(bq.offer("D"));//插入不会失败但是会返回false

        System.out.println(bq.peek());
        System.out.println(bq.poll());
        System.out.println(bq.offer("D"));*/

        //阻塞
/*        //插入队列阻塞
          //当队列满时
        bq.put("a");
        bq.put("b");
        bq.put("c");
        bq.put("d");

    //移除队列阻塞
        //当队列为空时
        System.out.println(bq.take());*/

        //超时
      /*  System.out.println(bq.offer("a"));
        System.out.println(bq.offer("b"));
        System.out.println(bq.offer("c"));
        System.out.println(bq.offer("a",3L, TimeUnit.SECONDS));*/
        System.out.println(bq.poll(3L, TimeUnit.SECONDS));


    }
}
