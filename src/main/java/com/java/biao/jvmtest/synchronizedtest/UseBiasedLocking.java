package com.java.biao.jvmtest.synchronizedtest;

import org.openjdk.jol.info.ClassLayout;


public class UseBiasedLocking {
    public static void main(String[] args) {
        UseBiasedLocking synchronizedUpgrade = new UseBiasedLocking();
        synchronizedUpgrade.lockUpgradeTest3();
    }

    public void lockUpgradeTest3() {
        // JVM默认4秒后才可以偏向锁，所以这里休眠5秒，锁对象就是偏向锁了
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Object object = new Object();
        Thread t1 = new Thread(() -> {
            // 让主线程休眠了5s钟，保证偏向锁延迟取消
            // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为0101(5)，代表当前锁对象处于
            // 偏向锁且为轻量级锁的状态(01代表偏向锁，01代表无锁)
            // 但是偏向的线程id为0，说明还没有线程进行锁偏向
            System.out.println(Thread.currentThread().getName() + "开启偏向锁，偏向锁延迟时间后，对象信息" + ClassLayout.parseInstance(object).toPrintable());
            synchronized (object) {
                // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为0101(5)，代表当前锁对象处于
                // 偏向锁且为轻量级锁的状态(01代表偏向锁，01代表无锁)
                // 偏向的线程id有值了，是当前偏向的线程Id,这里依然保存着分代年龄的信息
                System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
            }
            // t1退出临界区后发现markword中的偏向线程id还是t1,markword依然没变，说明偏向锁状态不会自动释放锁
            System.out.println(Thread.currentThread().getName() + "开启偏向锁，已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
        }, "t1");
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("开始创建线程t2");
        Thread t2 = new Thread(() -> {
            // markword中的偏向线程id是t1,markword依然没变，说明偏向锁状态不会自动释放锁
            System.out.println(Thread.currentThread().getName() + "开启偏向锁，偏向锁延迟时间后，对象信息" + ClassLayout.parseInstance(object).toPrintable());
            synchronized (object) {
                // 因为偏向锁是不存在撤销操作的，它设计出来的目的就是使得当前偏向线程在进入临界区时只需要对比下
                // 偏向线程id即可进入不需要额外的锁操作。
                // 但是但凡有另外一个线程出现竞争这个锁，那么就会撤销偏向锁
                // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为1000(8)，代表当前锁对象处于
                // 轻量级锁，只需要看后面2位是00就知道了(00代表轻量级锁)
                // 可见如果偏向锁状态下如果有另一个线程想进入到临界区(并没有并发下的竞争)，对象锁立马会变成轻量级锁
                // 前面62位会指向线程栈中的lock record锁记录
                System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
            }
            // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为0001(1)
            // 可见轻量级锁会存在撤销操作
            System.out.println(Thread.currentThread().getName() + "开启偏向锁，已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
        }, "t2");
        t2.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("开始创建线程t3");
        Thread t3 = new Thread(() -> {
            // markword中的偏向线程id是t1,markword依然没变，说明偏向锁状态不会自动释放锁
            System.out.println(Thread.currentThread().getName() + "开启偏向锁，偏向锁延迟时间后，对象信息" + ClassLayout.parseInstance(object).toPrintable());
            synchronized (object) {
                // 因为偏向锁是不存在撤销操作的，它设计出来的目的就是使得当前偏向线程在进入临界区时只需要对比下
                // 偏向线程id即可进入不需要额外的锁操作。
                // 但是但凡有另外一个线程出现竞争这个锁，那么就会撤销偏向锁
                // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为1000(8)，代表当前锁对象处于
                // 轻量级锁，只需要看后面2位是00就知道了(00代表轻量级锁)
                // 可见如果偏向锁状态下如果有另一个线程想进入到临界区(并没有并发下的竞争)，对象锁立马会变成轻量级锁
                // 前面62位会指向线程栈中的lock record锁记录
                System.out.println(Thread.currentThread().getName() + "已获取到锁信息" + ClassLayout.parseInstance(object).toPrintable());
            }
            // 锁对象头中的MarkWord中后三位代表偏向锁和锁标志位的值为0001(1)
            // 可见轻量级锁会存在撤销操作
            System.out.println(Thread.currentThread().getName() + "开启偏向锁，已释放锁信息" + ClassLayout.parseInstance(object).toPrintable());
        }, "t3");
        t3.start();
    }
}
