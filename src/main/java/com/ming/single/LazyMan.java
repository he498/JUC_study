package com.ming.single;

import java.lang.reflect.Constructor;

//懒汉式单例
public class LazyMan {

    private static boolean check = false;

    private LazyMan(){
        //加锁
//        synchronized (LazyMan.class){
//            if (lazyMan!=null){
//                throw new RuntimeException("不要试图使用反射破坏异常");
//            }
//        }
        //增加 标志位 check
        synchronized (LazyMan.class){
            if (check == false){
                check = true;
            }
            else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }

        System.out.println(Thread.currentThread().getName() + "ok");
    }
    private volatile static LazyMan lazyMan;

    //双重检测锁模式 懒汉式单例 DCL懒汉式
    public static LazyMan getInstance(){
        if (lazyMan==null){
            synchronized (LazyMan.class){
                if(lazyMan==null){
                    lazyMan = new LazyMan(); //不是原子性操作
                    /**
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     *
                     * 当多线程下， 线程A lazyman 还没有构造完毕，B线程进来，就会走lazyman!=null 所以需要加上volatile 避免指令重排和保证可见性。
                     */
                }
            }

        }
        return lazyMan;
    }


//    //单线程下没问题，但多线程下会出问题，需要加锁
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                LazyMan.getInstance();
//            }).start();
//        }
//    }

    //反射获取！
    public static void main(String[] args) throws Exception {
//        LazyMan instance = LazyMan.getInstance();
//        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
//        declaredConstructor.setAccessible(true); //无视私有构造器
//        LazyMan instance2 = declaredConstructor.newInstance();
//        System.out.println(instance);
//        System.out.println(instance2);
        //com.ming.single.LazyMan@1540e19d
        //com.ming.single.LazyMan@677327b6
        //解决办法：构造器加锁！


//        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true); //无视私有构造器
        LazyMan instance1 = declaredConstructor.newInstance();
        LazyMan instance2 = declaredConstructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance2);
//        com.ming.single.LazyMan@1540e19d
//        com.ming.single.LazyMan@677327b6
        //解决办法： 设置 一个标志位 private static boolean check = false;

        //若找到内部设置的隐藏的变量仍可以破解： Field check = LazyMan.class.getDeclaredField("check");
        //                               check.setAccessible(true);
        //                               LazyMan instance1 = declaredConstructor.newInstance();
        //                               check.set(instance,false);
        //                               LazyMan instance2 = declaredConstructor.newInstance();
    }
}

