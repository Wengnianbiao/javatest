import com.java.biao.springioc.Apple;
import com.java.biao.springioc.Fruits;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

        System.out.println("context 启动成功");
//        DefaultListableBeanFactory DefaultListableBeanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        Fruits apple = (Apple) context.getBean("apple");
        Fruits apple2 = (Apple) context.getBean("apple2");
        Fruits apple3 = (Apple) context.getBean("apple3");
        System.out.println(apple);
        System.out.println(apple2);
        System.out.println(apple3);
        apple.sellFruits();
        apple2.sellFruits();
        apple3.sellFruits();

    }
}
