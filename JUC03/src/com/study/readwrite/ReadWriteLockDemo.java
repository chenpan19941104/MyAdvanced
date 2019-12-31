package com.study.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private Map<String,String> map=new HashMap<>();
    private Lock lock=new ReentrantLock();
    private ReentrantReadWriteLock rwl=new ReentrantReadWriteLock();
    public void put(String key,String value ) throws InterruptedException {
        rwl.writeLock().lock();
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入结束");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            lock.unlock();
            rwl.writeLock().unlock();
        }
    }
    public void get(String key) throws InterruptedException {
        rwl.readLock().lock();
//        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读取");
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
            rwl.readLock().unlock();
        }
    }
}
/**
 * @author cp
 * @create 2019-12-16 19:37
 * 如何实现写一致 读并发呢
 * 多个线程同时读一个资源类没有任何问题，但是为了满足高并发量，读取共享资源应该同时进行
 * 但是
 * 如果有一个线程想去写共享资源，那么就不应该再有其他资源进行读或写
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 * 演示：
 *      1.不加锁，读乱写，读可以并发
 *      2.加lock，写可以，但是读并发下降
 *      3.加ReentrantReadWriteLock，写唯一，读并发高性能
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        for (int i = 1; i <=6 ; i++) {
            final int tempI=i;
            new Thread(() -> {
                try {
                    cache.put(tempI+"",tempI+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <=6 ; i++) {
            final int tempI=i;
            new Thread(() -> {
                try {
                    cache.get(tempI+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
