package com.java.biao.locktest;

/**
 * 线程A先后获取了监视器锁A和B，并调用监视器锁A的wait方法挂起当前线程只会释放监视器锁A的锁并不会释放B的
 * 现成B在sleep1s后获取锁A和B，获取监视器锁A会成功，但是会一直阻塞在锁B
 * 可以Debug查看线程的执行状态会发现线程A处于wait状态，线程B则处理Monitor状态
 */
public class LockTest1 {

    // 创建资源
    private static volatile  Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        // 创建线程
        Thread threadA = new Thread(() -> {
            try {
                // 获取resourceA共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadA get resourceA lock");
                    // 获取resourceB共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("threadA get resourceB lock");
                        // 线程A阻塞，并释放获取到的resourceA的锁
                        System.out.println("threadA release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA");
        // 创建线程
        Thread threadB = new Thread(() -> {
            try {
                //休眠1s
                Thread.sleep(1000);
                // 获取resourceA共享资源的监视器锁
                synchronized (resourceA) {
                    System.out.println("threadB get resourceA lock");
                    System.out.println("threadB try get resourceB lock...");
                    // 获取resourceB共享资源的监视器锁
                    synchronized (resourceB) {
                        System.out.println("threadB get resourceB lock");
                        // 线程B阻塞，并释放获取到的resourceA的锁
                        System.out.println("threadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadB");
        // 启动线程
        threadA.start();
        threadB.start();
        // 等待两个线程结束
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }
}
