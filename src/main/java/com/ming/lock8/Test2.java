package com.ming.lock8;

import java.util.concurrent.TimeUnit;

/**
 *
 *  3、增加hello方法,hello没有锁（普通方法）先执行hello然后再发短信
 *  4、两个对象，两个同步方法  //先打电话
 *
 */


public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(()->{
            phone1.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
//            phone2.hello();
            phone2.call();
        },"B").start();

    }


}


class Phone2{
    // synchronized 锁的对象是方法的调用者！
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

    //不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}