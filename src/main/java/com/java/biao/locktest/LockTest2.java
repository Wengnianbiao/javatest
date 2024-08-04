package com.java.biao.locktest;

import lombok.SneakyThrows;

/**
 * 线程A和B获取监视器锁资源A，并都调用了wait方法挂起线程，线程c获取到锁资源后调用notify方法唤醒监视器锁A中等待队列的线程，注意这里还让线程C调用notify后
 * 又睡了一秒钟，从结果可以看出调用notify方法后并还没有释放掉锁的占用，只有执行完同步块的代码后还会释放锁，释放完锁后，线程A和B中只有一个线程需要重新竞争
 * 锁资源以此执行完剩余代码，notify只会唤醒其中一个线程，但具体是谁就不一定了全看操作系统调度？
 */
public class LockTest2 {
    // 创建资源
    private static volatile Object resourceA = new Object();
    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            public void run() {
                // 获取resourceA共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    try {
                        System.out.println("threadA begin wait");
                        resourceA.wait();
                        System.out.println("threadA end wait");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        // 创建线程
        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    try {
                        System.out.println("threadB begin wait");
                        resourceA.wait();
                        System.out.println("threadB end wait");
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        // 创建线程
        Thread threadC = new Thread(new Runnable() {
            @SneakyThrows
            public void run() {
                synchronized (resourceA) {
                    System.out.println("threadC begin notify");
                    resourceA.notify();
                    Thread.sleep(1000);
                    System.out.println("线程C还没释放锁？");
                }
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        // 等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");
    }
}
