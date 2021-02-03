package com.example.current.curr.example.thread;

public class T13 extends Thread {
    @Override
    public void run() {

        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("我要退出了");
                break;
            }
            System.out.println("i= " + (i + 1));
        }

    }

    public static void main(String[] args) {

        try {
            T13 t11 = new T13();
            t11.start();
            Thread.sleep(1000);
            t11.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
