package com.java.biao.jvmtest.methodcall;

/**
 * 方法动态分派演示:重载是JVM根据实际类型来进行动态分派，依赖invokevirtual指令完成多态
 * invokevirtual指令的运行时解析过程大致分为以下几步：
 * 1)找到操作数栈顶的第一个元素所指向的对象的实际类型，记作C。
 * 2)如果在类型C中找到与常量中的描述符和简单名称都相符的方法，则进行访问权限校验，如果通过则返回这个方法的直接引用，
 * 查找过程结束；不通过则返回java.lang.IllegalAccessError异常。
 * 3)否则，按照继承关系从下往上依次对C的各个父类进行第二步的搜索和验证过程。4)如果始终没有找到合适的方法，则抛出java.lang.AbstractMethodError异常。
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        // man的静态类型是Human，实际类型是Man，调用方法时使用的指令是invokevirtual指令，这个指令的执行逻辑会
        // 在运行期间根据引用的实际类型来确定确定调用方法的版本。
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
