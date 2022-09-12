package com.ming.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //怎么启动callable?
        // new Thread(new Runnable).start();
        // new Thread(new FutureTask<V>( Callable )).start();
        new Thread().start();
        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        //需要适配类 FutureTask
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start(); //结果会被缓存，效率高
        Integer o = (Integer) futureTask.get(); //会阻塞
        System.out.println(o);
    }
}

//这里的泛型为call方法的返回值类型
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call!");
        return 1024;
    }
}
