package com.ming.function;

import java.util.function.Consumer;

/**
 * Consumer 消费型接口： 只有传入参数，没有返回值。
 */
public class Demo03 {
    public static void main(String[] args) {
        Consumer<String> consumer = (str)->{
            System.out.println(str);
        };

        consumer.accept("1111");

    }

}
