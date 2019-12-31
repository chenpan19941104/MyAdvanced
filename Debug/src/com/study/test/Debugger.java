package com.study.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author cp
 * @create 2019-12-20 21:19
 */
public class Debugger {
    public static void main(String[] args) {
        Debugger debugger=new Debugger();
        debugger.t1();
         List list=new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public void t1(){
        System.out.println();
    }
}
