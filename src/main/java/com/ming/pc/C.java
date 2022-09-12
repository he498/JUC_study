package com.ming.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A执行完调用B，B执行完调用C，C执行完调用A
 */

public class C {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{
            for (int i = 1 ; i < 10 ; i++){
                data.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1 ; i < 10 ; i++){
                data.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1 ; i < 10 ; i++){
                data.printC();
            }
        },"C").start();
    }
}

//资源类 lock
class Data3{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(){
        lock.lock();
        try {
            //业务判断-> 执行-> 通知
            while(number != 1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAAAAAA");
            //唤醒指定的人B
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            //业务判断-> 执行-> 通知
            while (number != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBBBBBB");
            //唤醒指定的人C
            number = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            //业务判断-> 执行-> 通知
            while (number != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCCCCCC");
            //唤醒指定的人A
            number = 1;
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
