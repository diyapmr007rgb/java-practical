class MyResource implements AutoCloseable {

    public void use() {
        System.out.println("Resource is being used.");
    }

    @Override
    public void close() {
        System.out.println("Resource closed.");
    }
}

public class Main {

    public static void main(String[] args) {

        try (MyResource resource = new MyResource()) {

            resource.use();

            throw new Exception("Original error occurred.");

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}