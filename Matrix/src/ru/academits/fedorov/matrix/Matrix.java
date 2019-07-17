package ru.academits.fedorov.matrix;

import ru.academits.fedorov.vector.Vector;

import java.util.Arrays;

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

    public Matrix(double[][] array) {
        vectors = new Vector[array.length];

        for (int i = 0; i < array.length; ++i) {
            vectors[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int n = vectors.length;
        if (n <= 0) {
            throw new IllegalArgumentException("Vectors count <= 0");
        }
        this.vectors = new Vector[n];

        int m = 0;
        for (Vector vector : vectors) {
            int size = vector.getSize();
            if (m < size) {
                m = size;
            }
        }

        for (int i = 0; i < n; ++i) {
            this.vectors[i] = new Vector(m, vectors[i].getValues());
        }
    }

    public int[] getSize() {
        return new int[]{vectors.length, vectors[0].getSize()};
    }

    public Vector getLine(int index) {
        if (index < 0 || index > vectors.length - 1) {
            throw new IllegalArgumentException("Out of range");
        }
        return vectors[index];
    }

    public void setLine(int index, Vector vector) {
        if (index < 0 || index > vectors.length - 1) {
            throw new IllegalArgumentException("Out of range");
        }

        vectors[index] = new Vector(vectors[0].getSize(), vector.getValues());
    }

    public Vector getColumn(int index) {
        if (index < 0 || index > vectors[0].getSize() - 1) {
            throw new IllegalArgumentException("Out of range");
        }

        Vector vector = new Vector(vectors.length);
        for (int i = 0; i < vectors.length; ++i) {
            vector.setValue(i, vectors[i].getValue(index));
        }
        return vector;
    }

    public void transpose() {
        int m = vectors[0].getSize();
        Vector[] vectorsT = new Vector[m];

        for (int i = 0; i < m; ++i) {
            vectorsT[i] = this.getColumn(i);
        }

        vectors = vectorsT;
    }

    public void scalarMultiplication(double alpha) {
        for (int i = 0; i < vectors.length; ++i) {
            vectors[i].scalarMultiplication(alpha);
        }
    }

    public double getDeterminant() {
        if (vectors.length != vectors[0].getSize()) {
            throw new IllegalArgumentException("M != N. The Matrix does not have determinant.");
        }

        Vector[] vectors = new Vector[this.vectors.length];
        for (int i = 0; i < vectors.length; ++i) {
            vectors[i] = new Vector(this.vectors[i]);
        }

        double res = 1;
        for (int i = 0; i < vectors.length - 1; ++i) {
            for (int j = i + 1; j < vectors.length; ++j) {
                if (vectors[j].getValue(i) == 0) {
                    continue;
                }
                double alpha = -vectors[i].getValue(i) / vectors[j].getValue(i);
                res /= alpha;
                vectors[j].scalarMultiplication(alpha);
                vectors[j].add(vectors[i]);

                if (i == j - 1) {
                    if (vectors[j].getValue(j) == 0) {
                        return 0;
                    }
                }
            }
        }

        for (int i = 0; i < vectors.length; ++i) {
            res *= vectors[i].getValue(i);
        }
        return res;
    }

    public Vector dot(Vector vector) {
        if (vector.getSize() != vectors[0].getSize()) {
            throw new IllegalArgumentException(String.format("Vector size != %d", vectors[0].getSize()));
        }
        Vector vectorRes = new Vector(vectors.length);

        for (int i = 0; i < vectors.length; ++i) {
            vectorRes.setValue(i, Vector.dot(vector, vectors[i]));
        }

        return vectorRes;
    }

    public void add(Matrix matrix) {
        if (matrix.vectors.length != vectors.length || matrix.vectors[0].getSize() != vectors[0].getSize()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        for (int i = 0; i < vectors.length; ++i) {
            vectors[i].add(matrix.vectors[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.vectors.length != vectors.length || matrix.vectors[0].getSize() != vectors[0].getSize()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        for (int i = 0; i < vectors.length; ++i) {
            vectors[i].subtract(matrix.vectors[i]);
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors.length != matrix2.vectors.length || matrix1.vectors[0].getSize() != matrix2.vectors[0].getSize()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        Matrix matrixRes = new Matrix(matrix1);
        matrixRes.add(matrix2);

        return matrixRes;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors.length != matrix2.vectors.length || matrix1.vectors[0].getSize() != matrix2.vectors[0].getSize()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        Matrix matrixRes = new Matrix(matrix1);
        matrixRes.subtract(matrix2);

        return matrixRes;
    }

    public static Matrix dot(Matrix matrix1, Matrix matrix2) {
        if (matrix1.vectors[0].getSize() != matrix2.vectors.length) {
            throw new IllegalArgumentException("Wrong matrices dimension.");
        }

        Matrix matrixRes = new Matrix(matrix1.vectors.length, matrix2.vectors[0].getSize());

        for (int i = 0; i < matrix1.vectors.length; ++i){
            for (int j = 0; j < matrix2.vectors[0].getSize(); ++j) {
                double value = Vector.dot(matrix1.vectors[i], matrix2.getColumn(j));
                matrixRes.vectors[i].setValue(j, value);
            }
        }
        return matrixRes;
    }

    @Override
    public String toString() { // TODO Передалать после Vector review
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < vectors.length; ++i) {
            if (i != 0) {
                result.append(" ");
            }
            result.append(vectors[i].toString());
            if (i == vectors.length - 1) {
                break;
            }
            result.append(", " + System.lineSeparator());
        }
        result.append("}");
        return result.toString();
    }
}
