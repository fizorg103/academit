package ru.academits.fedorov.shapes.Comporator;

import ru.academits.fedorov.shapes.Shape;

import java.util.Comparator;

public class ShapePerimeterComporator implements Comparator<Shape> {

    @Override
    public int compare(Shape shape1, Shape shape2) {
        Float area1 = new Float(shape1.getPerimeter());
        Float area2 = new Float(shape2.getPerimeter());
        return area1.compareTo(area2);
    }
}
