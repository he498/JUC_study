package com.ming.pool;

import java.util.concurrent.*;

// Executors 工具类，三大方法
//使用了线程池后，使用线程池来创建线程
public class Demo01 {
    public static void main(String[] args) {
        // Executors 三大方法
////        ExecutorService threadPool = Executors.newSingleThreadExecutor(); //单个线程
////        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池大小
//        ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩，自适应线程池大小
//
//        try {
//            for (int i = 0; i < 10; i++) {
//                threadPool.execute(()->{
//                    System.out.println(Thread.currentThread().getName()+" ok");
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //关闭线程池
//            threadPool.shutdown();
//        }


        /**
         * 四种拒绝策略：
         * 1、AbortPolicy()  默认的拒绝策略 ， 队列满了还有人进来，不处理这个人，抛出异常 java.util.concurrent.RejectedExecutionException
         * 2、CallerRunsPolicy() 哪来的去哪里， main执行就main 线程处理 main ok
         * 3、DiscardPolicy() 队列满了不会丢出异常，会丢掉任务
         * 4、DiscardOldestPolicy() 队列满了，尝试去和最早的竞争 也不会抛出异常
         */
        //手动创建线程池，自定义线程池
        //最大线程该如何定义：
        // 1、CPU密集型
        // 2、IO 密集型
        //获取CPU核数
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()); //拒绝策略

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+ " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }
}
