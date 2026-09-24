import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {

    @Column(name = "name")
    String name;

    @Column(name = "age")
    int age;

    @Column(name = "city")
    String city;

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}

public class AdvancedStudent {

    public static void main(String[] args) {

        String[] header = {"name", "age", "city"};
        String[] data = {"Diya", "20", "Anand"};

        Student student = new Student();

        Map<String, String> row = new HashMap<>();

        for (int i = 0; i < header.length; i++) {
            row.put(header[i], data[i]);
        }

        Field[] fields = Student.class.getDeclaredFields();

        for (Field field : fields) {

            if (field.isAnnotationPresent(Column.class)) {

                Column column = field.getAnnotation(Column.class);

                String columnName = column.name();

                if (!row.containsKey(columnName)) {
                    System.out.println(
                        "Missing column: " + columnName
                    );
                    continue;
                }

                String value = row.get(columnName);

                try {
                    field.setAccessible(true);

                    if (field.getType() == String.class) {
                        field.set(student, value);
                    }
                    else if (field.getType() == int.class) {
                        field.setInt(student, Integer.parseInt(value));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        student.display();
    }
}