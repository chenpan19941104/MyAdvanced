package com.study.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WaitAndNotify {
    //随机输出随机数  键盘输入停止，键盘再输入运行
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread t2 = new Thread(t1);
        t2.start();
        Thread2 t3 = new Thread2(t1);
        Thread t4 = new Thread(t3);
        t4.start();
    }
}

class Thread1 implements Runnable {
    private boolean flag = false;

    /**
     * @param
     * @return
     * @author cp
     */
    @Override
    public void run() {
        while (true) {
            if (flag) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Math.random() + 10);
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }
}

class Thread2 implements Runnable {
    private Thread1 t1;

    public Thread2(Thread1 t1) {
        this.t1 = t1;
    }

    @Override
    public void run() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
            System.out.println("键盘输入了");
            t1.setFlag(true);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
            System.out.println("键盘又输入了");
            t1.setFlag(false);
            synchronized (t1) {
                t1.notify();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Thread3 implements Runnable{

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i:"+i);
            Thread.currentThread().getName();
        }
    }
}
