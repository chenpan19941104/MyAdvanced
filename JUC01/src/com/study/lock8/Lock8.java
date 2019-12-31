package com.study.lock8;

import java.util.concurrent.TimeUnit;

class Phone{

    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        //休眠3秒，尽量不要使用sleep，因为需要计算，TimeUnit.SECONDS.sleep(4);
        System.out.println("sendEmail..");
    }
    public static synchronized void sendSms(){
        System.out.println("sendSms..");
    }
    public void hello(){
        System.out.println("hello");
    }
}

/**
 * 题目：多线程8锁
 * 1.标准访问，先打印邮件还是短信  --邮件
 * 2.email新增睡眠4s，先打印邮件还是短信 --邮件
 * 3.新增普通的hello方法，先打印邮件还是hello --hello
 * 4.两部手机，先打印邮件还是短信 --短信
 * 5. 两个静态同步方法，1部手机，先打印邮件还是短信 --邮件
 * 6. 两个静态同步方法，2部手机，先打印邮件还是短信 --邮件
 * 7. 1普通同步方法，1静态同步方法，1部手机，先打印邮件还是短信 --短信
 * 8. 1普通同步方法，1静态同步方法，2部手机，先打印邮件还是短信 --短信
 *
 * 笔记
 * 1-2
 *    一个对象里面如果有多个synchronized方法，某一时刻内，只要有一个线程去调用其中的一个synchronized方法，
 *    其他的线程都只能等待，换句话说，某一个时刻内，只能有一个线程去访问这些synchronized方法，
 *    所得是当前对象yis，被锁定后，其他的线程都不能进入当前对象的其他的synchronized方法
 *  3-4
 *      加个普通方法后发现和同步锁无关
 *      换成两个对象后，不是同一把锁了，情况立即变化
 *  5-6
 *      都换成静态同步方法，情况又变化
 *      若是普通同步方法，new this，具体的一部手机，所有的普通同步方法用的都是同一把锁-实例对象本身。
 *      若是静态同步方法，static Class，唯一的一个模板
 *      synchronized是实现同步的基础：java中的每个对象都可以作为锁
 *      具体表现为一下3种形式：
 *      对于普通同步方法，锁的是当前实例对象
 *      对于同步方法块，锁的就是synchronized括号里面配置的对象
 *      对于静态同步方法，锁是当前类的Class对象本身
 * 7-8
 *      当一个线程试图访问同步代码时他首先必须得到锁，退出或抛出异常时必须释放锁
 *
 *      所有的普通同步方法用的都是同一把锁-实例对象本身，就是new出来的
 *      也就是说如果一个实例对象的普通方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放锁后才能获取锁
 *      可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用的是不同的锁，所以不用等待该实例对象获取他们自己的锁
 *
 *      所有的静态同步方法用的也是同一把锁-类对象本身，就是我们说过的唯一模板Class
 *      具体的实例对象this和唯一模板class，这两把锁是两个不同的对象，所以静态同步方法和普通的同步方法之间是不会有竞态条件的
 *      但是一旦一个静态同步方法获取锁后，其他的静态同步方法必须等待该方法释放锁后才能获取锁
 */
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone=new Phone();
        Phone phone2=new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        Thread.sleep(100);
        new Thread(()->{
           //phone.sendSms();
            //phone.hello();
            phone2.sendSms();
        },"B").start();
    }
}
