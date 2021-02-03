package com.example.current.learn;

import lombok.SneakyThrows;

public class ThreadLocalTest {


    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>() {
        @Override
        protected Object initialValue() {
            System.out.println("调用get()时，当前线程共享变量没有设置，调initialValue获取默认值");
            return null;
        }
    };

    static class MyStringTask implements Runnable {
        private String name;

        MyStringTask(String name) {
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                if (threadLocal.get() == null) {
                    threadLocal.set("a");
                    System.out.println("线程" + name + ":a");

                } else {
                    String str = (String) threadLocal.get();
                    threadLocal.set(str + "a");
                    System.out.println("线程" + name + ":" + threadLocal.get());
                }
                Thread.sleep(800);
            }
        }
    }


    static class MyIntegerTask implements Runnable {
        private String name;

        MyIntegerTask(String name) {
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                // ThreadLocal.get方法获取线程变量
                if (threadLocal.get() == null) {
                    threadLocal.set(0);
                    System.out.println("线程" + name + ": 0");

                } else {
                    int num = (int) threadLocal.get();
                    threadLocal.set(num + 1);
                    System.out.println("线程" + name + ":" + threadLocal.get());
                    if (i == 3) {
                        threadLocal.remove();
                    }
                }
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new MyIntegerTask("IntegerTask1")).start();
        new Thread(new MyStringTask("StringTask1")).start();
        new Thread(new MyIntegerTask("IntegerTask2")).start();
        new Thread(new MyStringTask("StringTask2")).start();
    }

}
