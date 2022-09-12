package com.ming.demo01;

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类
        Ticket ticket = new Ticket();


        //@FunctionalInterface 函数时接口,jdk1.8 lambda表达式

        new Thread(()-> {
            for (int i = 1;i < 40; i++){
                ticket.sale();
            }
        },"A").start();
        new Thread(()-> {
            for (int i = 1;i < 40; i++){
                ticket.sale();
            }
        },"B").start();
        new Thread(()-> {
            for (int i = 1;i < 40; i++){
                ticket.sale();
            }
        },"C").start();
    }
}


//资源类 OOP
class Ticket{
    //属性、方法
    private int number = 50;


    //卖票的方式
    //synchronized 本质：队列和锁
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余："+number);
        }
    }

}