package com.example.current.learn;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class Semaphore07 {

    private static Semaphore semaphore;

    public static void main(String[] args) {
        Park parking = new Park(3);
        for (int i = 0; i < 6; i++) {
            new Car(parking).start();
        }
    }

    static class Park {
        Park(int count) {
            semaphore = new Semaphore(count);
        }

        @SneakyThrows
        public void park() {
            try {
                semaphore.acquire();
                long time = (long) (Math.random() * 1000);
                System.out.println(Thread.currentThread().getName() + "进入停车场，停车" + time + "秒...");
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "开出停车场...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

    }

    static class Car extends Thread {
        Park parking;

        Car(Park parking) {
            this.parking = parking;
        }

        @Override
        public void run() {
            parking.park();
        }
    }


}
