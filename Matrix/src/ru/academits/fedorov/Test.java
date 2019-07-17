package ru.academits.fedorov;

import ru.academits.fedorov.matrix.Matrix;
import ru.academits.fedorov.vector.Vector;

import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Vector[] vectors1 = {new Vector(new double[]{1, 2, 3, 4, 5}),
                            new Vector(new double[]{6, 7, 8, 9, 10}),
                            new Vector(new double[]{1, 2, 4, 5, 8}),
                            new Vector(new double[]{1, 3, 4, 5, 6})};
        Matrix matrix1 = new Matrix(vectors1);

        Vector[] vectors2 = {new Vector(new double[]{1, 2}),
                            new Vector(new double[]{6, 7}),
                            new Vector(new double[]{1, 2}),
                            new Vector(new double[]{1, 3}),
                            new Vector(new double[]{1, 3})};

        Matrix matrix2 = new Matrix(vectors2);
        Matrix matrixRes = Matrix.dot(matrix1,matrix2);
        System.out.println(matrixRes.toString());
    }
}
