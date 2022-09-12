package com.ming.lock;

import java.util.concurrent.TimeUnit;

//T1==>mylock
//T2==>mylock
//T1==>myunlock
//T2==>myunlock
public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {
        SpinlockDemo lock = new SpinlockDemo();

        new Thread(()->{
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }

        },"T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            lock.myLock();
            try {

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }

        },"T2").start();
    }
}
