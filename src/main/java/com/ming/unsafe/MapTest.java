package com.ming.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//java.util.ConcurrentModificationException


public class MapTest {
    public static void main(String[] args) {
        //加载因子，初始化容量
        //默认等价于 new HashMap<>(16,0.75);
//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 10 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }
}
