package com.example.current.curr.example.thread;

public class T14 extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 200000; i++) {
            System.out.println("i= " + (i + 1));
        }
    }

    public static void main(String[] args) {
        try {
            T14 thread = new T14();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("是否停止1？:" + thread.isInterrupted());
            System.out.println("是否停止2？:" + thread.isInterrupted());

        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");

    }
}
