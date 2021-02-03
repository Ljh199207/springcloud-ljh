package com.example.springfeign;

import org.springframework.stereotype.Component;

@Component
public class FallBackHy implements HelloInterface {

    @Override
    public String sayHiFromClientOne(String name) {
        return "AB";
    }
}
