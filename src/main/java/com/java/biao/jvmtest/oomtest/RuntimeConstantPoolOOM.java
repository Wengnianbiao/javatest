package com.java.biao.jvmtest.oomtest;

public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 首次出现,str1会现在堆中创建一个String对象
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
