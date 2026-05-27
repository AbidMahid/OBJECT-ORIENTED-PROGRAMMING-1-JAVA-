abstract class Shape {
    abstract double calculateArea();
    abstract double calculatePerimeter();
    
    public void display() {
        System.out.println("Area: " + calculateArea());
        System.out.println("Perimeter: " + calculatePerimeter());
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double length, width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    double calculateArea() {
        return length * width;
    }
    
    @Override
    double calculatePerimeter() {
        return 2 * (length + width);
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(5), new Rectangle(4, 6)};
        
        for (Shape shape : shapes) {
            shape.display();
            System.out.println("---");
        }
    }
}