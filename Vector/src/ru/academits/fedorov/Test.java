package ru.academits.fedorov;

import ru.academits.fedorov.vector.Vector;

import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        double[] array1 = {1, 2, 3};
        double[] array2 = {4, 7, 2, 3, 4};

        Vector vector1 = new Vector(array1);
        Vector vector1Copy = new Vector(vector1);
        Vector vector2 = new Vector(array2);

        System.out.println("Вектор 1: " + vector1.toString() + " размер: " + vector1.getSize() + " длина: " + vector1.getLength());
        System.out.println("Вектор 2: " + vector2.toString() + " размер: " + vector2.getSize() + " длина: " + vector2.getLength());

        vector1.add(vector2);
        System.out.println("Прибавим к Вектору 1 Вектор 2: " + vector1.toString());

        vector1.mulByNumber(5);
        System.out.println("Умножим на 5" + vector1.toString());

        vector1.subtract(vector2);
        Vector vector3 = new Vector(vector2);
        System.out.println("Отнимем Вектор 2: " + vector1.toString());
        System.out.print(vector2 == vector3);
        System.out.print(" ");
        System.out.println(vector3.equals(vector2));

        Vector vector4 = Vector.add(vector1Copy, vector2);
        System.out.println(vector4.toString());

        Vector vector5 = Vector.subtract(vector1Copy, vector2);
        System.out.println(vector5.toString());

        double dotResult = Vector.dot(vector1Copy, vector2);
        System.out.println(dotResult);
    }
}
