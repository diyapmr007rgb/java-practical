
import java.util.Arrays;
import java.util.EmptyStackException;

class GenericStack<T> {
    private Object[] elements;
    private int top;

    public GenericStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "Capacity must be positive");
        }

        elements = new Object[capacity];
        top = 0;
    }

    public void push(T value) {
        if (top == elements.length) {
            throw new IllegalStateException("Stack is full");
        }

        elements[top++] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T value = (T) elements[--top];
        elements[top] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return (T) elements[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }
}

public class GenericStackDemo {
    public static void main(String[] args) {
        GenericStack<Integer> numbers = new GenericStack<>(5);

        numbers.push(10);
        numbers.push(20);
        numbers.push(30);

        System.out.println("Top number: " + numbers.peek());
        System.out.println("Popped number: " + numbers.pop());
        System.out.println("Stack size: " + numbers.size());

        GenericStack<String> names = new GenericStack<>(5);

        names.push("Amit");
        names.push("Neha");

        System.out.println("Popped name: " + names.pop());

        // Handle empty stack
        while (!names.isEmpty()) {
            System.out.println("Popped: " + names.pop());
        }

        try {
            names.pop();
        } catch (EmptyStackException e) {
            System.out.println("Stack is empty!");
        }
    }
}
