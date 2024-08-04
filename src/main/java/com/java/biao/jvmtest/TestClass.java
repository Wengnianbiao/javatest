package com.java.biao.jvmtest;

public class TestClass {

    private final Object obj = new Object();

    private int m;

    public int inc() {
        synchronized (obj) {
            m = m + 1;
            System.out.println(obj.hashCode());
        }
        return m;
    }

    public static void main(String[] args) throws InterruptedException {
        TestClass testClass = new TestClass();
        Thread thread1 = new Thread(() -> {
            int inc = testClass.inc();
        });
        Thread thread2 = new Thread(() -> {
            int inc = testClass.inc();
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }
}
