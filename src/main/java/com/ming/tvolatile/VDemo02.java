package com.ming.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

//不保证原子性
public class VDemo02 {
    //原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger();
    public static void add(){
        num.getAndIncrement(); // +1 方法 底层用的是 CAS
    }
    //理论上互不干扰，结果为2w ,多线程存在同步执行问题，volatile 不保证原子性  //main 19886
    public static void main(String[] args) {
        for (int i = 1; i <= 20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){ //main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);


    }

}
