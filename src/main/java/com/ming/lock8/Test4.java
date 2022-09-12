package com.ming.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1、1个静态同步方法1个普通同步方法，一个对象
 * 2、1个静态同步方法1个普通同步方法，两个对象
 */
public class Test4 {
    public static void main(String[] args) {
        //两个对象的Class模板只有一个，static
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(()->{
            phone1.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{

//            phone.call();
            phone2.call();
        },"B").start();

    }


}

//Phone3 唯一的一个class对象
class Phone4{

    //静态同步方法 锁的是class 类模板，两个用的不是同一个锁
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("发短信");
    }

    //普通同步方法
    public synchronized void call(){
        System.out.println("打电话");
    }



}
