package com.ming.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    private volatile static int num = 0; //加入volatile 后可以保证在线程中 num 变量一直是最新的 ,不加入时线程不知道num已经改变会一直循环执行
    public static void main(String[] args) { //main 线程


        new Thread(()->{
            while (num==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1;
        System.out.println(num);

    }
}
