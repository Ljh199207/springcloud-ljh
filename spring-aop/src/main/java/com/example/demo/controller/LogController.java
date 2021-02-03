package com.example.demo.controller;

import com.example.demo.annotation.AsyncLog;
import com.example.demo.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {


    @Log("执行方法一")
    @GetMapping("/one")
    public void methodOne(String name) {

    }

    @Log("执行方法二")
    @GetMapping("/two")
    public void methodTwo() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Log("执行方法三")
    @GetMapping("/three")
    public void methodThree(String name, String age) {

    }


    @AsyncLog("执行方法四")
    @GetMapping("/four")
    public String methodFour(String name, String age) {
        return "abc";
    }
}
