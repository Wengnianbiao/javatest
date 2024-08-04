package com.java.biao.springioc;

/**
 * 罗森超市
 */
public class RawsonMarketServiceImpl implements MarketService {

    // 共有三个成员变量
    private String name;
    private Integer age;
    private Fruits apple;

    // 创建有参的构造器,通过在xml中通过有参的构造方法插入
    public RawsonMarketServiceImpl(String name, Integer age, Fruits apple) {
        this.name = name;
        this.age = age;
        this.apple = apple;
    }

    public RawsonMarketServiceImpl() {
        System.out.println("罗森超市创建了");
    }

    public void sellFood() {
        System.out.println("service 中的 sellFood 方法执行了"+this.name + " "+ this.age + " " + this.apple);
        this.apple.sellFruits();
    }
}
