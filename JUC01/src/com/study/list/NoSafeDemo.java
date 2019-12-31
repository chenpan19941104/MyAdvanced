package com.study.list;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.故障现象
     *java.util.ConcurrentModificationException
 * 2.导致原因
    * 没加锁
    *toString没加锁+遍历读写不一致，导致报错
 * 3.解决方法
    *3.1 new Vector()  不用
    *3.2 Collections.synchronizedList(new ArrayList<><>)  不用
    *3.3 CopyOnWriteArrayList 写时复制技术，juc使用
        * 写时复制：
        *CopyOnWrite容器即写时复制的容器，往一个容器里面添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器的object[]进行copy，
        *复制出一个新的容器Object[] newElements，然后新容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器，setArray(newElements);
        * 这样做的好处就是可以对copyOnWrite容器进行并发的读，
        * 而不需要加锁，因为当前容器不会添加任何元素，所以copyonwrite容器也是一种读写分离的思想，读和写不同的容器
        *public boolean add(E e) {
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *             Object[] elements = getArray();
 *             int len = elements.length;
 *             Object[] newElements = Arrays.copyOf(elements, len + 1);
 *             newElements[len] = e;
 *             setArray(newElements);
 *             return true;
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 * 4.优化建议
 */
public class NoSafeDemo {
    public static void main(String[] args) {
        //hashmap
        Map<String, String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();
        for (int i = 1; i <10 ; i++) {
            final int tempI=i;
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),tempI+"");
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
     /*
     //hashset
        Set<String> set= new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());//new CopyOnWriteArraySet();// new HashSet<>();
        for (int i = 1; i <10 ; i++) {
            final int tmpI = i;
            new Thread(() -> {
                set.add(tmpI + "");
                System.out.println(Thread.currentThread().getName() + "\t" + set);
            }, String.valueOf(i)).start();
        }*/
        /*
        //ArraList
        List<String> list = new ArrayList<>();//new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList());//new Vector<>();//new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.forEach(System.out::println);
        for (int i = 1; i <=30 ; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(Thread.currentThread().getName()+list);
            }, String.valueOf(i)).start();
        }*/

    }
}
