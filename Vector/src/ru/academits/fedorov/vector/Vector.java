package ru.academits.fedorov.vector;

import java.util.Arrays;

public class Vector {
    private double[] values;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Argument N <= 0.");
        }
        values = new double[n];
        for (int i = 0; i < n - 1; ++i) {
            values[i] = 0;
        }
    }

    public Vector(Vector vector) {
        values = Arrays.copyOf(vector.values, vector.values.length);
    }

    public Vector(double[] values) {
        this.values = Arrays.copyOf(values, values.length);
    }

    public Vector(int n, double[] values) {
        this.values = Arrays.copyOf(values, n);
    }

    public int getSize() {
        return values.length;
    }

    public void add(Vector vector) {
        int minIndex = Math.min(values.length, vector.values.length);
        for (int i = 0; i < minIndex; ++i) {
            if (i < vector.values.length) {
                values[i] += vector.values[i];
            }
        }
    }

    public void subtract(Vector vector) {
        int minIndex = Math.min(values.length, vector.values.length);
        for (int i = 0; i < minIndex; ++i) {
            if (i < vector.values.length) {
                values[i] -= vector.values[i];
            }
        }
    }

    public void scalarMultiplication(double alpha) {
        for (int i = 0; i < values.length; ++i) {
            values[i] *= alpha;
        }
    }

    public void expand() {
        for (int i = 0; i < values.length; ++i) {
            values[i] *= -1;
        }
    }

    public double getLength() {
        double length = 0;
        for (double value : values) {
            length += Math.pow(value, 2);
        }
        return Math.sqrt(length);
    }

    public double getValue(int index) {
        if (index < 0 || index > values.length - 1) {
            throw new IllegalArgumentException("index out of range");
        } else {
            return values[index];
        }
    }

    public void setValue(int index, double value) {
        if (index < 0 || index > values.length - 1) {
            throw new IllegalArgumentException("index out of range");
        } else {
            values[index] = value;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (values.length != vector.values.length) {
            return false;
        }

        for (int i = 0; i < values.length; ++i) {
            if (values[i] != vector.values[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        int length = values.length;
        for (int i = 0; i < length; ++i) {
            result.append(String.format("%.5f", values[i]));
            if (i == length - 1) {
                break;
            }
            result.append(", ");
        }
        result.append("}");
        return result.toString();
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vectorRes;
        if (vector1.values.length > vector2.values.length) {
            vectorRes = new Vector(vector1);
            vectorRes.add(vector2);
            return vectorRes;
        }

        vectorRes = new Vector(vector2);
        vectorRes.add(vector1);
        return vectorRes;
}

    public static Vector subtract(Vector vector1, Vector vector2) {
        Vector vectorRes;
        if (vector1.values.length > vector2.values.length){
            vectorRes = new Vector(vector1);
            vectorRes.subtract(vector2);
        }

        vectorRes = new Vector(vector2);
        vectorRes.expand();
        vectorRes.add(vector1);

        return vectorRes;
    }

    public static double dot(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.values.length, vector2.values.length);
        double res = 0;
        for (int i = 0; i < minSize; ++i) {
            res += vector1.values[i] * vector2.values[i];
        }
        return res;
    }
}