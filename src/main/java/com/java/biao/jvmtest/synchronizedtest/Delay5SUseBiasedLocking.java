package com.java.biao.jvmtest.synchronizedtest;

import org.openjdk.jol.info.ClassLayout;


public class Delay5SUseBiasedLocking {
    public static void main(String[] args) {
        Delay5SUseBiasedLocking synchronizedUpgrade = new Delay5SUseBiasedLocking();
        synchronizedUpgrade.lockUpgradeTest2();
    }

    // 在偏向锁延迟时间之前获取锁
    // 偏向锁存在延迟时间，所以这种情况和不使用偏向锁的情况是一样的
    public void lockUpgradeTest2() {
        Object obj = new Object();
        System.out.println("开启偏向锁，偏向锁延迟时间前，对象信息");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj) {
            System.out.println("已获取到锁信息");
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        System.out.println("开启偏向锁，已释放锁信息");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
