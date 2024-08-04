package com.java.biao.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloWorld {

    private static int num = 0;
    public String name = "HelloWorld";

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Son son = new Son();
        Class<? extends Son> aClass = son.getClass();
        Method setMyName = aClass.getMethod("setMyName", String.class);
        setMyName.invoke(son, "www");
    }
}
