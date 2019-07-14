import ru.academits.fedorov.comporator.ShapeAreaComparator;
import ru.academits.fedorov.comporator.ShapePerimeterComparator;
import ru.academits.fedorov.shape.*;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[8];

        shapes[0] = new Square(1);
        shapes[1] = new Square(1.5);
        shapes[2] = new Rectangle(1,2);
        shapes[3] = new Rectangle(0.5,3);
        shapes[4] = new Circle(1.0);
        shapes[5] = new Circle(2);
        shapes[6] = new Triangle(0, 0, 1, 0, 0, 1);
        shapes[7] = new Triangle(1.2, 2.8, 3.1, 4.0, 0, 5);

        Arrays.sort(shapes, new ShapeAreaComparator());
        int index = 1;
        System.out.println("Данные о фигуре с максимальной площадью");
        Shape shape = shapes[shapes.length - index];
        System.out.println(shape.toString());
        printInfo(shape);
        System.out.println();


        Arrays.sort(shapes, new ShapePerimeterComparator());
        index = 2;
        System.out.println("Данные о фигуре с вторым по величине периметром");
        shape = shapes[shapes.length - index];
        System.out.println(shape.toString());
        printInfo(shape);
        System.out.println();
    }

    private static void printInfo(Shape shape){
        System.out.println("Ширина:   " + shape.getWidth());
        System.out.println("Высота:   " + shape.getHeight());
        System.out.println("Периметр: " + shape.getPerimeter());
        System.out.println("Площадь:  " + shape.getArea());
    }
}
