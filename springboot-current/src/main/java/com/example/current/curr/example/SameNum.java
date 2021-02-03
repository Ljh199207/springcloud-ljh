package com.example.current.curr.example;

public class SameNum {

    private static int i = 5;

    public static void main(String[] args) {

        /***
         * printLn 方法内是同步的，但是i--在println之前发生，有可能出现线程不安全
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i= " + (i--) + "threadName" + Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i= " + (i--) + "threadName" + Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i= " + (i--) + "threadName" + Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i= " + (i--) + "threadName" + Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("i= " + (i--) + "threadName" + Thread.currentThread().getName());
            }
        }).start();
    }
}
