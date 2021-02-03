package com.example.springribbon.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class App {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clz = Class.forName("com.example.springribbon.reflect.ReflectTest");
        System.out.println("类全名-->" + clz.getName());
        System.out.println("类简名-->" + clz.getSimpleName());

        Field[] fields = clz.getDeclaredFields();
        Arrays.stream(fields).forEach(e -> System.out.println("全部字段名->" + e.getName()));

        Field[] fields1 = clz.getFields();
        Arrays.stream(fields1).forEach(e -> System.out.println("公共字段名->" + e.getName()));

        Method[] methods = clz.getDeclaredMethods();
        Arrays.stream(methods).forEach(e -> System.out.println("全部方法名->" + e.getName()));

        Method[] methods1 = clz.getMethods();
        Arrays.stream(methods1).forEach(e -> System.out.println("公共方法名->" + e.getName()));
    }
}
