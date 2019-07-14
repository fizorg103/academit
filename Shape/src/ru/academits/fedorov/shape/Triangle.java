package ru.academits.fedorov.shape;

import java.util.Arrays;

public class Triangle extends Shape {
    private double[] r1 = new double[2];
    private double[] r2 = new double[2];
    private double[] r3 = new double[2];

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        r1[0] = x1;
        r1[1] = y1;

        r2[0] = x2;
        r2[1] = y2;

        r3[0] = x3;
        r3[1] = y3;
    }

    @Override
    public double getWidth() {
        double[] array = {r1[0], r2[0], r3[0]};
        return getMax(array) - getMin(array);
    }

    @Override
    public double getHeight() {
        double[] array = {r1[1], r2[1], r3[1]};
        return getMax(array) - getMin(array);
    }

    @Override
    public double getArea() {
        double perimeterHalf = getPerimeter() / 2;
        return Math.sqrt(perimeterHalf * (perimeterHalf - getDistance(r1, r2)) * (perimeterHalf - getDistance(r1, r3)) * (perimeterHalf - getDistance(r2, r3)));
    }

    @Override
    public double getPerimeter() {
        return getDistance(r1, r2) + getDistance(r1, r3) + getDistance(r2, r3);
    }

    @Override
    public String toString() {
        return "Triangle#" + r1[0] + "," + r1[1] + ":" + r2[0] + "," + r2[1] + ":" + r3[0] + "," + r3[1];
    }

    private double getMax(double[] array) {
        return Arrays.stream(array).max().getAsDouble();
    }

    private double getMin(double[] array) {
        return Arrays.stream(array).min().getAsDouble();
    }

    private double getDistance(double[] r1, double[] r2) {
        return Math.sqrt(Math.pow(r1[1] - r2[1], 2) + Math.pow(r1[0] - r2[0], 2));
    }
}
