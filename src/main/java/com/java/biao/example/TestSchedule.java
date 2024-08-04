package com.java.biao.example;

public class TestSchedule {
    public static void main(String[] args) throws ClassNotFoundException {
        User[] users = new User[]{
                new User("111"), new User("222"), new User("333")
        };
        //初始化对象数组
        User[] target = new User[users.length];
        //新建一个目标对象数组

        System.arraycopy(users, 0, target, 0, users.length); // 复制

        System.out.println("数组地址是否一样：" + (users == target ? "浅复制" : "深复制"));
        System.out.println("数组内对象地址是否一样：" + (users[0] == target[0] ? "浅复制" : "深复制"));

        target[0].setEmail("444");
        System.out.println("修改后输出 users[0] ,是否和 target[0]一样是444，users[0]：" + users[0].getEmail());

        users[0] = null; // -----------------①
        System.out.println("遍历 users");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("遍历 target");
        for (User user : target) {
            System.out.println(user);
        }

        users = null;
        if (target == null) {
            System.out.println("users = null 后是否 target 也变成了 null,是则证明是浅复制");
        } else {
            System.out.println("users = null 后是否 target 不受任何影响,是则再次证明数组是深复制");
        }
    }
}

class User {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User [email=" + email + "]";
    }
}
