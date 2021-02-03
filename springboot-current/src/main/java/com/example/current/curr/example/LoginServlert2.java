package com.example.current.curr.example;

import com.example.current.curr.annoations.UnThreadSafe;
import lombok.SneakyThrows;

@UnThreadSafe
public class LoginServlert2 {


    private static String nameRef;
    private static String passwordRef;

    public synchronized static void doPost(String name, String password) throws InterruptedException {
        nameRef = name;
        if (name.equals("a")) {
            Thread.sleep(3000);
        }
        passwordRef = password;
        System.out.println("userName : " + nameRef + ",password :" + password);
    }

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                doPost("a", "aa");
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                doPost("b", "bb");
            }
        }).start();
    }
}
