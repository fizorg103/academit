package ru.academits.fedorov.shapes.Comporator;

import ru.academits.fedorov.shapes.Shape;

import java.util.Comparator;

public class ShapePerimeterComporator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        Double area1 = new Double(shape1.getPerimeter());
        Double area2 = new Double(shape2.getPerimeter());
        return area1.compareTo(area2);
    }
}
