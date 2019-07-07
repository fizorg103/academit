package ru.academits.fedorov;


public class Square extends Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return width;
    }

    @Override
    public double getArea() {
        return width * width;
    }

    @Override
    public double getPerimeter() {
        return 4 * width;
    }

    @Override
    public String toString(){
        return "Квадрат со стороной = " + width;
    }
}
