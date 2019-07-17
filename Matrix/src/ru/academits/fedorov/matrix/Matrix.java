package ru.academits.fedorov.matrix;

import ru.academits.fedorov.vector.Vector;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("M or N <= 0.");
        }

        vectors = new Vector[n];
        for (int i = 0; i < n; ++i) {
            vectors[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; ++i) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    @Override
    public String toString() { // TODO Передалать после Vector review
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < vectors.length; ++i) {
            result.append(vectors[i].toString());
            if (i == vectors.length - 1) {
                break;
            }
            result.append(", ");
        }
        result.append("}");
        return result.toString();
    }
}
