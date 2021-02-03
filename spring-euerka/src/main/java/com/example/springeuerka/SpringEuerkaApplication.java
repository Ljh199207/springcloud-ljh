package com.example.springeuerka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@SpringBootApplication
@EnableEurekaServer
public class SpringEuerkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEuerkaApplication.class, args);
    }

}
