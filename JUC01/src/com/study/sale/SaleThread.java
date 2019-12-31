package com.study.sale;

public class SaleThread implements Runnable {
    private  Integer nums=30;
    @Override
    public void run() {

        while(true){
            synchronized (this.getClass()){
                if(nums<=0){
                    System.out.println("票已经卖光了");
                    break;
                }else {
                    System.out.println(Thread.currentThread().getName()+"卖了一张票,还剩"+(--nums)+"张票");
                }
            }
        }
    }
}
