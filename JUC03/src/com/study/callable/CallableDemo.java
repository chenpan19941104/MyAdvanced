package com.study.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("callable...");
        return 1024;
    }
}
/**
 * @author cp
 * @create 2019-12-17 8:25
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask f=new FutureTask(new MyThread());
        new Thread(f,"A").start();
        System.out.println(f.get());
    }
}
