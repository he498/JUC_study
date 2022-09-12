package com.ming.function;

import java.util.function.Function;

/**
 * Function 函数型接口，有一个输入参数，有一个输出
 * 可以用lambda简化
 */
public class Demo01 {
    public static void main(String[] args) {
//        Function<String,String> function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };

        Function<String,String> function = (str) -> {return  str;};
        System.out.println(function.apply("asd"));

    }


}
