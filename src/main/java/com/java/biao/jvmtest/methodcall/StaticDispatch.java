package com.java.biao.jvmtest.methodcall;

/**
 * 静态分派测试:最典型应用表现就是方法重载:
 * 方法重载是在编译器就确定了使用哪个重载方法，确定的方式是通过方法的参数类型即静态类型确定(我觉得可以理解为形参)
 * StaticDispatch有三个重载方法
 */
public class StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        // man的引用类型是Human，也称为静态类型为Human，但实际类型是Man(运行时类型)
        Human man = new Man();
        Human woman = new Woman();
        // 在方法重载时是通过参数的静态类型而不是实际类型作为判定依据的。
        // 由于静态类型在编译期可知，所以在编译阶段，Javac编译器就根据参数的静态类型决定了会使用哪个重载版本
        // 因此选择了sayHello(Human)作为调用目标，并把这个方法的符号引用写到main()方法里的两条invokevirtual指令的参数中
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

        // 如果对他们进行强制转化，输出结果就会是
        // hello,gentleman!
        // hello,lady!
        sr.sayHello((Man)man);
        sr.sayHello((Woman)woman);

        System.out.println();
    }
}
