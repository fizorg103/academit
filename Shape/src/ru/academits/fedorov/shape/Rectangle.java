package ru.academits.fedorov.shape;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString(){
        return "Rectangle#" + width + ":" + height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Rectangle temp = (Rectangle) o;
        return temp.width == width && temp.height == height;
    }

    @Override
    public int hashCode() {
        final int prime = 29;
        int hash = 1;
        hash += hash * prime + Double.hashCode(width);
        hash += hash * prime + Double.hashCode(height);
        return hash;
    }
}
