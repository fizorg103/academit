package ru.academits.fedorov.shapes.Comporator;

import ru.academits.fedorov.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComporator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        Double area1 = shape1.getArea();
        Double area2 = shape2.getArea();
        return area1.compareTo(area2);
    }
}
