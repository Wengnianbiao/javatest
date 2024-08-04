package com.java.biao.example;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Son extends Father{

    public String myName;
    public static String staticName;

    @Override
    public void fit() {
        System.out.println("子类方法输出");
    }
}
