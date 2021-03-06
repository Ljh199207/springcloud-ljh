package com.example.current.curr.example.thread;

public class CountOperate extends Thread {

    public CountOperate() {
        System.out.println("CountOperate -- begin");
        System.out.println("Thread.currentThread().getName() ：" + Thread.currentThread().getName());
        System.out.println("this.getName()" + this.getName());
        System.out.println("CountOperate -- end");
    }

    @Override
    public void run() {
        System.out.println("run -- begin");
        System.out.println("Thread.currentThread().getName() ：" + Thread.currentThread().getName());
        System.out.println("this is isAlive:" + this.isAlive());

        System.out.println("this.getName()" + this.getName());
        System.out.println("run -- end");
    }


    public static void main(String[] args) {

        CountOperate countOperate = new CountOperate();
        Thread thread = new Thread(countOperate);
        System.out.println("begin: "+thread.isAlive());
        thread.setName("A");
        thread.start();
        System.out.println("end: "+thread.isAlive());
    }
}
