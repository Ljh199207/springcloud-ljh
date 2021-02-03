package com.example.current.curr.example.thread;

public class T11 extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 50000; i++) {
            System.out.println("i= " + (i + 1));
        }
    }

    public static void main(String[] args) {

        try {
            T11 t11 = new T11();
            t11.start();
            Thread.sleep(2000);
            Thread.interrupted();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }

    }
}
