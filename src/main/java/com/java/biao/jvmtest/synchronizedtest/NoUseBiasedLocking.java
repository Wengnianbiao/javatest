package com.java.biao.jvmtest.synchronizedtest;

import org.openjdk.jol.info.ClassLayout;


public class NoUseBiasedLocking {
    public static void main(String[] args) {
        NoUseBiasedLocking synchronizedUpgrade = new NoUseBiasedLocking();
        synchronizedUpgrade.lockUpgradeTest1();
    }

    // -XX:-UseBiasedLocking 关闭偏向锁设置
    // 未开启偏向锁设置的话会将无锁状态直接升级成轻量级锁
    public void lockUpgradeTest1() {
        Object obj = new Object();
        System.out.println("未开启偏向锁，对象信息");
        // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为001，代表当前锁对象处于
        // 无偏向且无锁的状态(0代表无偏向锁，01代表无锁)
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj) {
            System.out.println("已获取到锁信息");
            // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为000，代表当前锁对象处于
            // 无偏向且为轻量级锁的状态(0代表无偏向锁，00代表偏向锁)
            // MarkWord前面的位信息其实是代表的是指向当前线程栈帧中的lock record锁记录的地址
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
        System.out.println("已释放锁信息");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
