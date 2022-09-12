package com.ming.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1","2","3");
//        list.forEach(System.out::println);
        //开启多线程报错

        // 并发下 ArrayList 不安全
        /**
         *  解决方案：
         *  1、 List<String> list = new Vector<>();  //不推荐
         *  2、 List<String> list = Collections.synchronizedList(new ArrayList<>());
         *  3、JUC解决方案： List<String> list = new CopyOnWriteArrayList<>();
         */
//        List<String> list = new ArrayList<>();
        //CopyOnWrite 写入时复制， cow 计算机程序设计领域的一种优化策略
//        多个线程调用的时候，list读取的时候是固定的，写入的时候会发生覆盖操作
        List<String> list =new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
