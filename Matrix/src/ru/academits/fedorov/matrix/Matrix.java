package ru.academits.fedorov.matrix;

import ru.academits.fedorov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("M or N <= 0.");
        }

        rows = new Vector[n];
        for (int i = 0; i < n; ++i) {
            rows[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array.length <= 0.");
        }

        if (array[0] == null) {
            throw new IllegalArgumentException("Array[0].length <= 0.");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; ++i) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] lines) {
        int n = lines.length;
        if (n <= 0) {
            throw new IllegalArgumentException("Vectors count <= 0");
        }
        this.rows = new Vector[n];

        int m = 0;
        for (Vector vector : lines) {
            int size = vector.getSize();
            if (m < size) {
                m = size;
            }
        }

        for (int i = 0; i < n; ++i) {
            this.rows[i] = new Vector(m, lines[i].getValues());
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Out of range");
        }
        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Out of range");
        }

        rows[index] = new Vector(getColumnsCount(), vector.getValues());
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Out of range");
        }

        Vector vector = new Vector(rows.length);
        for (int i = 0; i < rows.length; ++i) {
            vector.setValue(i, rows[i].getValue(index));
        }
        return vector;
    }

    public void transpose() {
        int m = getColumnsCount();
        Vector[] vectorsT = new Vector[m];

        for (int i = 0; i < m; ++i) {
            vectorsT[i] = this.getColumn(i);
        }
        rows = vectorsT;
    }

    public void mulByNumber(double alpha) {
        for (Vector row : rows) {
            row.mulByNumber(alpha);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new Error("M != N. The Matrix does not have determinant.");
        }

        Vector[] vectors = new Vector[this.rows.length];
        for (int i = 0; i < vectors.length; ++i) {
            vectors[i] = new Vector(this.rows[i]);
        }

        double epsilon = 1e-30;
        double res = 1;
        for (int i = 0; i < vectors.length - 1; ++i) {
            for (int j = i + 1; j < vectors.length; ++j) {
                if (vectors[j].getValue(i) == 0) {
                    continue;
                }
                double alpha = -vectors[i].getValue(i) / vectors[j].getValue(i);
                res /= alpha;
                vectors[j].mulByNumber(alpha);
                vectors[j].add(vectors[i]);

                if (i == j - 1) {
                    if (Math.abs(vectors[j].getValue(j)) < epsilon) {
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
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException(String.format("Vector size != %d", rows[0].getSize()));
        }
        Vector vectorRes = new Vector(rows.length);

        for (int i = 0; i < rows.length; ++i) {
            vectorRes.setValue(i, Vector.dot(vector, rows[i]));
        }

        return vectorRes;
    }

    public void add(Matrix matrix) {
        if (matrix.rows.length != rows.length || matrix.getColumnsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        for (int i = 0; i < rows.length; ++i) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.rows.length != rows.length || matrix.getColumnsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        for (int i = 0; i < rows.length; ++i) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        Matrix matrixRes = new Matrix(matrix1);
        matrixRes.add(matrix2);

        return matrixRes;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Matrices must be one size.");
        }

        Matrix matrixRes = new Matrix(matrix1);
        matrixRes.subtract(matrix2);

        return matrixRes;
    }

    public static Matrix dot(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Wrong matrices dimension.");
        }

        Matrix matrixRes = new Matrix(matrix1.rows.length, matrix2.rows[0].getSize());

        for (int i = 0; i < matrix1.rows.length; ++i) {
            for (int j = 0; j < matrix2.getColumnsCount(); ++j) {
                double value = Vector.dot(matrix1.rows[i], matrix2.getColumn(j));
                matrixRes.rows[i].setValue(j, value);
            }
        }
        return matrixRes;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");

        for (int i = 0; i < rows.length; ++i) {
            if (i != 0) {
                result.append(" ");
            }
            result.append(rows[i].toString());
            if (i == rows.length - 1) {
                break;
            }
            result.append(", ");
            result.append(System.lineSeparator());
        }
        result.append("}");
        return result.toString();
    }
}