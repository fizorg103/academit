package ru.academits.fedorov.comporator;

import ru.academits.fedorov.shape.Shape;

import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        Double area1 = shape1.getPerimeter();
        Double area2 = shape2.getPerimeter();
        return area1.compareTo(area2);
    }
}
