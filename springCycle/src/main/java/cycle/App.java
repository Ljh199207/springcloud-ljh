package cycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("circle/circle.xml");
        A object = (A) applicationContext.getBean("cycleA");
        object.cycleA();
    }
}
