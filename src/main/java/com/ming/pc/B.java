package com.ming.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  线程之间的通信问题，生产者和消费者问题！ 等待唤醒，通知唤醒
 *  线程交替执行 A   B 操作同一个变量 num = 0
 * A num+1
 * B num -1
 */

public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()-> {
            for (int i = 0;i < 10; i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()-> {
            for (int i = 0;i < 10; i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()-> {
            for (int i = 0;i < 10; i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()-> {
            for (int i = 0;i < 10; i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();

    }



}

class Data2{ // 数字 资源类
    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
//    condition.await(); //等待
//    condition.signalAll(); //唤醒

    //+1
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0){
                condition.await();
                // 等待
            }
            number++;
            //通知其他线程，+1完毕
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    //-1
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //业务代码
            while (number == 0){
                condition.await();
                //等待
            }
            number--;
            // 通知其他线程，-1完毕
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }



}
