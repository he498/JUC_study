package com.ming.lock8;

import java.util.concurrent.TimeUnit;

/**
 *
 *  先发短信再打电话
 *
 *
 */


public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(()->{
            phone.call();
        },"B").start();

    }


}


class Phone{
    //锁的对象是方法的调用者！
    //两个方法用同一个phone的锁，谁先拿到谁执行

    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}