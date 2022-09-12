package com.ming.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {
    //CAS compareAndSet : 比较并交换
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2020);
//        //期望的值 ，更新的值
////        public final boolean compareAndSet(int expect, int update)
//        //如果期望的值达到了，那么就更新，否则就不更新，CAS是CPU的并发原语
//        //true
//        //2021
//        //false
//        //2021
////        System.out.println(atomicInteger.compareAndSet(2020, 2021));
////        System.out.println(atomicInteger.get());
////        System.out.println(atomicInteger.compareAndSet(2020, 2021));
////        System.out.println(atomicInteger.get());
//
//        //=======================ABA问题======================
//        //乐观锁！
//        //捣乱的线程
//        System.out.println(atomicInteger.compareAndSet(2020, 2021));
//        System.out.println(atomicInteger.get());
//
//        System.out.println(atomicInteger.compareAndSet(2021, 2020));
//        System.out.println(atomicInteger.get());
//
//
//        //===================期望的线程=========================
//        System.out.println(atomicInteger.compareAndSet(2020, 2021));
//        System.out.println(atomicInteger.get());


        //如果泛型是包装类，注意引用类型
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); //获得版本号
            System.out.println("a1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a2=>"+atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a3=>"+atomicStampedReference.getStamp());

        },"a").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("b1=>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));
            System.out.println("b1=>"+atomicStampedReference.getStamp());

        },"b").start();

    }
}
