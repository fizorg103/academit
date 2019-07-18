package ru.academits.fedorov.shape;


public class Square implements Shape {
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
    public String toString() {
        return "Square#" + side;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Square temp = (Square) o;
        return temp.side == side;
    }

    @Override
    public int hashCode() {
        final int prime = 29;
        int hash = 1;
        hash = hash * prime + Double.hashCode(side);
        return hash;
    }
}
