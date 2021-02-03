package com.example.current.curr.example.thread;

public class T12 extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 50000; i++) {
            System.out.println("i= " + (i + 1));
        }
    }

    public static void main(String[] args) {

        try {
            T12 t11 = new T12();
            t11.start();
            Thread.sleep(2000);
            t11.interrupt();
            System.out.println("是否停止1？:"+t11.interrupted());
            System.out.println("是否停止2？:"+t11.interrupted());

        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }

    }
}
