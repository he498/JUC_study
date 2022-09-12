package com.ming.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// enum? => 枚举本事也是Class类
public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }
}

class Test{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingle instance1 = EnumSingle.INSTANCE;
//        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();
        //Exception in thread "main" java.lang.NoSuchMethodException: com.ming.single.EnumSingle.<init>()
        //没有空参构造器，需要去源码分析  javap -p EnumSingle.class 发现里面也有空参构造器
        // 需要使用jad 反编译 发现里面是一个有参构造器

        //最后成功获得预期的报错：  Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        //不能破坏枚举的单例
        System.out.println(instance1);
        System.out.println(instance2);

    }
}
