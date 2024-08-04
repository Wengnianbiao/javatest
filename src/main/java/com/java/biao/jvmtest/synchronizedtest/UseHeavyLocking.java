package com.java.biao.jvmtest.synchronizedtest;

import org.openjdk.jol.info.ClassLayout;

public class UseHeavyLocking {
    public static void main(String[] args) {
        UseHeavyLocking synchronizedUpgrade = new UseHeavyLocking();
        synchronizedUpgrade.lockUpgradeTest4();
    }

    public void lockUpgradeTest4() {
        // JVM默认4秒后才可以偏向锁，所以这里休眠5秒，锁对象就是偏向锁了
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "加锁前对象信息" + ClassLayout.parseInstance(object).toPrintable());
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
                try {
                    // 让t2线程启动后并竞争锁
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + "已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
        }, "t1");
        t1.start();
        try {
            // 让t1线程先启动并拿到锁
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "加锁前对象信息" + ClassLayout.parseInstance(object).toPrintable());
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
            }
            System.out.println(Thread.currentThread().getName() + "已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
        }, "t2");
        t2.start();
    }


    public void lockUpgradeTest5() {
        // JVM默认4秒后才可以偏向锁，所以这里休眠5秒，锁对象就是偏向锁了
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "加锁前对象信息" + ClassLayout.parseInstance(object).toPrintable());
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
                try {
                    // 让t2线程启动后并竞争锁
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + "已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
        }, "t1");
        t1.start();
        try {
            // 让t1线程先启动并拿到锁
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "加锁前对象信息" + ClassLayout.parseInstance(object).toPrintable());
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
            }
            System.out.println(Thread.currentThread().getName() + "已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
        }, "t2");
        t2.start();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "加锁前对象信息" + ClassLayout.parseInstance(object).toPrintable());
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
                }
                System.out.println(Thread.currentThread().getName() + "已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
            }, "t3_" + i).start();
        }

    }
}
