package com.example.springribbon.reflect;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReflectTest {

    private String name;

    public Integer age;

    protected String bir;

    String sex;

    public void test() {
        System.out.println("test-----");
    }

    public String test(Map<String, Object> name) {
        System.out.println("test-----" + name.get("name"));
        return (String) name.get("name");
    }

    private void test1() {
        System.out.println("test1-----");

    }

    protected void test2() {
        System.out.println("test2-----");
    }

    public ReflectTest() {
        System.out.println("construct-----");
    }

    public ReflectTest(String name) {
        System.out.println("construct param-----");
    }
}
