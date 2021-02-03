package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/email")
public class EmailController {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender jms;

    public EmailController(JavaMailSender jms) {
        this.jms = jms;
    }

    @RequestMapping("sendSimpleEmail")
    public String sendSimpleEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo("18523969719@163.com");
        simpleMailMessage.setSubject("就是一个标题");
        simpleMailMessage.setText("使用Spring Boot发送简单邮件的内容。");
        jms.send(simpleMailMessage);
        return "发送成功";
        //todo 未完待续
    }

}
