import java.util.Scanner;
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

   
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    double height;
    double width;

    Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    
    double area() {
        return height * width;
    }
}

class Triangle extends Shape {
    double height;
    double base;

    Triangle(double height, double base) {
        this.height = height;
        this.base = base;
    }

    
    double area() {
        return 0.5 * base * height;
    }
}

public class ShapeDemo {
    public static void main(String[] args) {

        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(10.0, 4.0),
            new Triangle(8.0, 6.0),
            new Circle(3.0)
        };

        double total = 0;
        double largest = 0;

        for (Shape shape : shapes) {
            double currentArea = shape.area();

            System.out.println("Area of = " + shape.getClass().getSimpleName() + " is " + currentArea);

            total = total + currentArea;

            if (currentArea > largest) {
                largest = currentArea;
            }
        }

        System.out.println("Total Area = " + total);
        System.out.println("Largest Area = " + largest);
    }
}