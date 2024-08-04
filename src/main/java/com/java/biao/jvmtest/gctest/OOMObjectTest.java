package com.java.biao.jvmtest.gctest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 内存占位符对象，一个OOMObject大约占64KB
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */

public class OOMObjectTest {

    public static void fillHeap(int num) throws InterruptedException {

        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }

    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
        System.gc();
        fillHeap(1000);
        System.gc();
        fillHeap(1000);
        System.gc();
    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }
}








