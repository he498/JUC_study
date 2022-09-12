package com.ming.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类
        Ticket2 ticket = new Ticket2();


        //@FunctionalInterface 函数时接口,jdk1.8 lambda表达式

        new Thread(()->{for (int i = 1;i < 40; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 1;i < 40; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 1;i < 40; i++) ticket.sale();},"C").start();

    }
}


// 使用lock锁
//lock 三部曲：
//1. new ReentrantLock();
//2. lock.lock(); //加锁
//3. finally => lock.unlock(); //解锁

class Ticket2 {
    //属性、方法
    private int number = 30;
    Lock lock = new ReentrantLock();
    //卖票的方式
    public void sale() {
        //加锁
        lock.lock();
        try {
            //业务代码
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "票,剩余：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //解锁
        }
    }
}
