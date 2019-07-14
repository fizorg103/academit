package ru.academits.fedorov.shape;


public class Square extends Shape {
    private double side;

    public Square(double width) {
        this.side = width;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString(){
        return "Square#" + side;
    }
}
