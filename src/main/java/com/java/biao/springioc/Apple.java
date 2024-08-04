package com.java.biao.springioc;

import lombok.Setter;

/**
 * 苹果
 */
@Setter
public class Apple implements Fruits {

    /**
     * 大小
     */
    private Integer size;

    /**
     * 大小
     */
    private String color;

    public Apple(Integer size, String color) {
        this.size = size;
        this.color = color;
    }

    public Apple() {
        System.out.println("生产苹果完成");
    }

    @Override
    public void sellFruits() {
        System.out.println("出售苹果:" + "大小-" + size + "颜色-" + color);
    }
}
