package com.study.parent;

/**
 * @author cp
 * @create 2019-12-18 19:33
 */
public class HelloJVM {
    public static void main(String[] args) {

        HelloJVM jvm = new HelloJVM();
        //自己编写的就叫做应用类加载器 appClassLoader
        System.out.println(jvm.getClass().getClassLoader());
        //扩展加载器
        System.out.println(jvm.getClass().getClassLoader().getParent());
        //根加载器
        System.out.println(jvm.getClass().getClassLoader().getParent().getParent());


        //返回的类加载器  为根加载器
        String s = new String();
        System.out.println(s.getClass().getClassLoader());

        Thread thread = new Thread();
        thread.start();
        //thread.start();

        byte[] bytes = new byte[1024 * 1024 *1024* 800];
        /*for (int i = 0; i <bytes.length ; i++) {
            System.out.println(bytes[i]);
        }*/
    }
}
