import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {

    @Run
    public void testOne() {
        System.out.println("Test One Running");
    }

    @Run
    public void testTwo() {
        System.out.println("Test Two Running");
    }

    public void normalMethod() {
        System.out.println("Normal method");
    }

    @Run
    public void testThree() {
        System.out.println("Test Three Running");
    }
}

public class MiniTEsterRunner {

    public static void main(String[] args) {

        MyTests obj = new MyTests();

        int count = 0;

        Method[] methods = MyTests.class.getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                try {
                    method.invoke(obj);
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Total tests ran: " + count);
    }
}