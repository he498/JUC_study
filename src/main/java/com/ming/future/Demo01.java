package com.ming.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *  异步调用 CompletableFuture
 *  //异步执行
 *  //成功回调
 *  //失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        //没有返回值的异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
//        });
//        System.out.println("11111"); //这里会先打印出来然后阻塞等待异步执行
//        completableFuture.get(); //获取执行结果

        // 有返回值. supplyAsync 异步回调
        // 成功和失败的回调
        // 返回的是错误信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"completableFuture=>Integer");
            int i = 10/0;
            return 1024;
        });

        completableFuture.whenComplete((t,u)->{
            System.out.println("t=>"+t);  //编译成功时回调的t 是正常成功的返回值   异常时t为null
            System.out.println("u=>"+u); //没有错误时是null , 错误时u为错误信息 u=>java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally(e->{
            System.out.println(e.getMessage()); //打印错误信息
            return 233; // 当错误时可以返回错误结果
        });
        /**
         *  200 500 404
         */


    }
}
