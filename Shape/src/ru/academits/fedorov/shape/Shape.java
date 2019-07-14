package ru.academits.fedorov.shape;

public class Shape {
    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    protected static boolean isEquals(double number1, double number2) {
        double epsilon = 1e-23;
        return (Math.abs(number1 - number2) < epsilon);
    }
}
