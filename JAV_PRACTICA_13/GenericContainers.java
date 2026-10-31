
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericContainers {

    // Generic method to return the first array element
    public static <T> T firstElement(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Integer Box: " + intBox.get());

        Box<String> strBox = new Box<>();
        strBox.set("Hello Java");
        System.out.println("String Box: " + strBox.get());

        Pair<String, Integer> pair =
            new Pair<>("Age", 20);

        System.out.println("Pair: " +
            pair.getFirst() + " = " + pair.getSecond());

        String[] names = {"Amit", "Neha", "Raj"};
        Integer[] numbers = {10, 20, 30};

        System.out.println("First name: " +
            firstElement(names));

        System.out.println("First number: " +
            firstElement(numbers));
    }
}
