package com.study.onebyone;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {
    private int flag = 1;//1是A 2是B 3是C
    private Lock lock = new ReentrantLock();
    //3把钥匙
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print() throws InterruptedException {
        lock.lock();
        try {
            int num = 0;
            if (flag == 1) {
                //判断
                while (flag != 1) {
                    c1.await();//A停止
                }
                //干活
                num = 5;
                for (int i = 1; i <= num; i++) {
                    System.out.println("A打印");
                }
                //通知
                flag = 2;
                c2.signal();
            } else if (flag == 2) {
                while (flag != 2) {
                    c2.await();//B停止
                }
                num = 10;
                for (int i = 1; i <= num; i++) {
                    System.out.println("B打印");
                }
                //通知
                flag = 3;
                c3.signal();
            } else {
                //判断
                while (flag != 3) {
                    c3.await();//C停止
                }
                //干活
                num = 15;
                for (int i = 1; i <= 15; i++) {
                    System.out.println("C打印");
                }
                //通知
                flag = 1;
                c1.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 * @author cp
 * @create 2019-12-16 19:17
 * <p>
 * * 精准唤醒
 * * 多线程之间按顺序调用，实现A先干->B次之->C最后
 * *
 * * 三个线程启动，要求如下：
 * *
 * * AA打印5次，BB打印10次，CC打印15次
 * * 接着
 * * AA打印5次，BB打印10次，CC打印15次
 * * ......来10轮
 * *
 * * 1    高聚低合前提下，线程操作资源类
 * * 2    判断/干活/通知
 * * 3    多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）
 * * 4    一定要注意，标志位的修改更新
 */
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource rs = new ShareResource();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 10; j++) {
                        rs.print();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
