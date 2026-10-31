import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    transient String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String toString() {
        return "ID: " + id +
               ", Name: " + name +
               ", Password: " + password;
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        Student[] students = {
            new Student(1, "Amit", "abc123"),
            new Student(2, "Neha", "xyz789"),
            new Student(3, "Raj", "pass456")
        };

        // Save objects to a file
        try (ObjectOutputStream out =
                 new ObjectOutputStream(
                     new FileOutputStream("students.dat"))) {

            out.writeObject(students);
            System.out.println("Objects saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read objects back into fresh objects
        try (ObjectInputStream in =
                 new ObjectInputStream(
                     new FileInputStream("students.dat"))) {

            Student[] loaded =
                (Student[]) in.readObject();

            System.out.println("\nLoaded students:");

            for (Student s : loaded) {
                System.out.println(s);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}