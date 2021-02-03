package com.example.current.learn;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch06 {

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    static class BossThread extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("Boss在会议室等待，总共有" + countDownLatch.getCount() + "个人开会...");
            countDownLatch.await();
            System.out.println("所有人都已经到齐了，开会吧...");
        }
    }

    static class EmpleoyeeThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "，到达会议室....");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        //Boss线程启动
        new BossThread().start();

        for (int i = 0; i < 5; i++) {
            new EmpleoyeeThread().start();
        }

    }

}
